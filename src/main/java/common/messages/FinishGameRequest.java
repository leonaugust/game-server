package common.messages;

import server.common.GameResult;

import java.util.*;

public class FinishGameRequest {

    public FinishGameRequest() {

    }

    public FinishGameRequest(GameResult result) {
        this.result = result;
    }

    public GameResult result;

}
