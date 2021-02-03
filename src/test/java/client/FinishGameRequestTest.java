package client;

import static org.junit.jupiter.api.Assertions.assertSame;

import common.messages.FinishGameRequest;
import common.messages.FinishGameResponse;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import platform.session.SessionMap;
import server.ServerApplication;
import server.common.GameResult;
import server.common.ProfileState;
import server.config.GameConfig;
import server.domain.UserProfile;

@SpringBootTest(classes = ServerApplication.class)
public class FinishGameRequestTest extends ConnectAndLoginTests {

  @Resource
  private SessionMap sessionMap;

  private UserProfile profile;

  @Resource
  private GameConfig gameConfig;

  @Test
  public void finishGameWhenUserIsNotPlayingTest() {
    successLoginTest();

    profile = (UserProfile) sessionMap.getSessionByProfileId(enterAccount.userProfile.id).profile;
    profile.setState(ProfileState.MAIN_MENU);

    FinishGameResponse response = clientConnection
        .request(new FinishGameRequest(GameResult.DEFEAT), FinishGameResponse.class);

    assertSame(0, response.award.energy);
    assertSame(0, response.award.money);
    assertSame(ProfileState.MAIN_MENU, profile.getState());
  }

  @Test
  public void finishGameWithResultWinTest() {
    successLoginTest();

    profile = (UserProfile) sessionMap.getSessionByProfileId(enterAccount.userProfile.id).profile;
    var experience = profile.getExperience();
    var rating = profile.getRating();
    var money = profile.getMoney();
    profile.setState(ProfileState.IN_GAME);

    FinishGameResponse response = clientConnection
        .request(new FinishGameRequest(GameResult.WIN), FinishGameResponse.class);

    assertSame(experience + gameConfig.getExperienceRewardWin(), profile.getExperience());
    assertSame(rating + gameConfig.getRatingResultWin(), profile.getRating());
    assertSame(money + gameConfig.getMoneyRewardWin(), profile.getMoney());
    assertSame(gameConfig.getMoneyRewardWin(), response.award.money);
    assertSame(ProfileState.MAIN_MENU, profile.getState());
  }

  @Test
  public void finishGameWithResultDefeatTest() {
    successLoginTest();

    profile = (UserProfile) sessionMap.getSessionByProfileId(enterAccount.userProfile.id).profile;
    var experience = profile.getExperience();
    var rating = profile.getRating();
    profile.setState(ProfileState.IN_GAME);

    FinishGameResponse response = clientConnection
        .request(new FinishGameRequest(GameResult.DEFEAT), FinishGameResponse.class);

    assertSame(experience + gameConfig.getExperienceRewardDefeat(), profile.getExperience());
    assertSame(Math.max(rating + gameConfig.getRatingResultDefeat(), gameConfig.getRatingMinimal()),
        profile.getRating());
    assertSame(0, response.award.money);
    assertSame(ProfileState.MAIN_MENU, profile.getState());
  }

}
