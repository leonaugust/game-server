package common.messages;

import server.domain.TopItem;

import java.util.*;

public class TopResponse {

    public List<TopItem> topList;

    public List<TopItem> getTopList() {
        return topList;
    }

    public void setTopList(List<TopItem> topList) {
        this.topList = topList;
    }
}
