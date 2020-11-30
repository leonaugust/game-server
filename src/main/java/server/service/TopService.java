package server.service;

import org.springframework.stereotype.Service;
import server.domain.TopItem;
import server.domain.UserProfile;

import java.util.*;

@Service
public class TopService {

    private List<TopItem> topList;

    public void onRatingChange(UserProfile profile){
        //toto update topList
    }

    public List<TopItem> getTopList(){
        //todo return TOP 10 items
        return Collections.emptyList();
    }
    
}
