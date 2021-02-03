package server.controller;

import common.messages.FinishGameRequest;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import platform.service.MessageController;
import server.domain.UserProfile;
import server.service.GameService;

@Service
public class FinishGameController implements MessageController<FinishGameRequest, UserProfile> {

  @Resource
  private GameService service;

  @Override
  public Object onMessage(FinishGameRequest finishGameRequest, UserProfile userProfile) {
    return service.finishGame(userProfile.id(), finishGameRequest.result);
  }

  @Override
  public Class<FinishGameRequest> messageClass() {
    return FinishGameRequest.class;
  }
}
