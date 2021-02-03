package client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertSame;

import common.messages.StartGameRequest;
import common.messages.StartGameResponse;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import platform.session.SessionMap;
import server.ServerApplication;
import server.common.ProfileState;
import server.domain.UserProfile;

@SpringBootTest(classes = ServerApplication.class)
public class StartGameRequestTest extends ConnectAndLoginTests {

  @Resource
  private SessionMap sessionMap;

  private UserProfile profile;

  @Test
  public void startGameWhenUserIsAlreadyPlayingTest() {
    successLoginTest();

    profile = (UserProfile) sessionMap.getSessionByProfileId(enterAccount.userProfile.id).profile;
    profile.setState(ProfileState.IN_GAME);

    StartGameResponse response = clientConnection
        .request(new StartGameRequest(), StartGameResponse.class);
    assertSame(1, response.errorCode);
    assertThat("User is already in the game.", equalToIgnoringCase(response.errorMessage));
  }

  @Test
  public void startGameWithEnergyNotEnoughTest() {
    successLoginTest();

    profile = (UserProfile) sessionMap.getSessionByProfileId(enterAccount.userProfile.id).profile;
    profile.setEnergy(4);

    StartGameResponse response = clientConnection
        .request(new StartGameRequest(), StartGameResponse.class);
    assertSame(1, response.errorCode);
    assertThat("Not enough energy!", equalToIgnoringCase(response.errorMessage));
  }

  @Test
  public void startGameWithFullEnergyTest() {
    successLoginTest();

    profile = (UserProfile) sessionMap.getSessionByProfileId(enterAccount.userProfile.id).profile;
    assertSame(25, profile.getEnergy());

    StartGameResponse response = clientConnection
        .request(new StartGameRequest(), StartGameResponse.class);
    assertSame(0, response.errorCode);
    assertSame(20, profile.getEnergy());
    assertSame(ProfileState.IN_GAME, profile.getState());
  }

}
