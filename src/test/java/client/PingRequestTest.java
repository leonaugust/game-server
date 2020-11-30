package client;

import common.messages.Ping;
import common.messages.Pong;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import server.ServerApplication;

@SpringBootTest(classes = ServerApplication.class)
public class PingRequestTest extends ConnectAndLoginTests {

    @Test
    public void pingPongTest() {
        successLoginTest();

        clientConnection.request(new Ping(), Pong.class);
    }

}
