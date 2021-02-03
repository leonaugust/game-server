package server.controller;

import common.messages.TopRequest;
import common.messages.TopResponse;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import platform.service.MessageController;
import server.domain.UserProfile;
import server.service.TopService;

@Service
public class TopController implements MessageController<TopRequest, UserProfile> {

  @Resource
  private TopService service;

  @Override
  public Object onMessage(TopRequest topRequest, UserProfile profile) {
    TopResponse response = new TopResponse();
    response.setTopList(service.getTopList());
    return response;
  }

  @Override
  public Class<TopRequest> messageClass() {
    return TopRequest.class;
  }

}
