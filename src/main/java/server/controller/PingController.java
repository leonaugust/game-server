package server.controller;

import common.messages.Ping;
import common.messages.Pong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import platform.service.MessageController;
import server.domain.UserProfile;

@Service
public class PingController implements MessageController<Ping, UserProfile> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object onMessage(Ping ping, UserProfile userProfile) {
        return new Pong();
    }

    @Override
    public Class<Ping> messageClass() {
        return Ping.class;
    }

}
