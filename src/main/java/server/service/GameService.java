package server.service;


import common.dto.AwardStructure;
import common.messages.FinishGameResponse;
import common.messages.StartGameResponse;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import platform.service.UserProfileRegistry;
import platform.session.SessionMap;
import server.common.GameResult;
import server.common.ProfileState;
import server.config.GameConfig;
import server.domain.UserProfile;

@Service
public class GameService {

  @Resource
  private GameConfig gameConfig;

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Resource
  private UserProfileRegistry registry;

  @Resource
  private ProfileService profileService;

  @Resource
  private SessionMap sessionMap;

  public StartGameResponse startGame(int profileId) {
    StartGameResponse response = new StartGameResponse();
    UserProfile profile = (UserProfile) sessionMap.getSessionByProfileId(profileId).profile;
    if (profile.getState() == ProfileState.IN_GAME) {
      response.errorCode = 1;
      response.errorMessage = "User is already in the game.";
      return response;
    }

    var energy = profile.getEnergy();
    if (energy < gameConfig.getEnergyPriceForGame()) {
      response.errorCode = 1;
      response.errorMessage = "Not enough energy!";
    } else {
      profile.setEnergy(energy - gameConfig.getEnergyPriceForGame());
      profile.setState(ProfileState.IN_GAME);
      registry.updateUserProfile(profile);
    }
    log.info("startGame for user {}, errorCode {}", profileId, response.errorCode);
    return response;
  }

  public FinishGameResponse finishGame(int profileId, GameResult result) {
    FinishGameResponse response = new FinishGameResponse();
    response.award = new AwardStructure();
    UserProfile profile = (UserProfile) sessionMap.getSessionByProfileId(profileId).profile;
    if (!profile.getState().equals(ProfileState.IN_GAME)) {
      return response;
    }

    var experience = profile.getExperience();
    var rating = profile.getRating();
    if (result == GameResult.WIN) {
      experience += gameConfig.getExperienceRewardWin();
      rating += gameConfig.getRatingResultWin();
      profile.setMoney(profile.getMoney() + gameConfig.getMoneyRewardWin());
    } else {
      experience += gameConfig.getExperienceRewardDefeat();
      rating = Math.max(rating + gameConfig.getRatingResultDefeat(), gameConfig.getRatingMinimal());
    }

    AwardStructure award = profileService.handleLevelUpCase(profile, experience);
    if (award.getMoney() != 0 || award.getEnergy() != 0) {
      response.award = award;
      profile.setMoney(profile.getMoney() + award.getMoney());
      profile.setEnergy(profile.getEnergy() + award.getEnergy());
    }

    profile.setRating(rating);
    profile.setState(ProfileState.MAIN_MENU);
    registry.updateUserProfile(profile);
    log.info("finishGame for user {}", profileId);
    return response;
  }



}
