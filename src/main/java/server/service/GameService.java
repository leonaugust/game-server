package server.service;

import common.messages.StartGameResponse;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import platform.service.UserProfileRegistry;
import platform.session.SessionMap;
import server.common.ProfileState;
import server.domain.UserProfile;

@Service
public class GameService {

  @Value("${energy.price.for.game}")
  private int energyPriceForGame;

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Resource
  private UserProfileRegistry registry;

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
    if (energy < energyPriceForGame) {
      response.errorCode = 1;
      response.errorMessage = "Not enough energy!";
    } else {
      profile.setEnergy(energy - energyPriceForGame);
      profile.setState(ProfileState.IN_GAME);
      registry.updateUserProfile(profile);
    }
    log.info("startGame for user {}, errorCode {}", profileId, response.errorCode);
    return response;
  }


}
