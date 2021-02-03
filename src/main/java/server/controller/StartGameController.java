package server.controller;

import common.messages.StartGameRequest;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import platform.service.MessageController;
import server.domain.UserProfile;
import server.service.GameService;

@Service
public class StartGameController implements MessageController<StartGameRequest, UserProfile> {

  @Resource
  private GameService service;

  @Override
  public Object onMessage(StartGameRequest startGameRequest, UserProfile userProfile) {
    return service.startGame(userProfile.id);
  }

  @Override
  public Class<StartGameRequest> messageClass() {
    return StartGameRequest.class;
  }
}
