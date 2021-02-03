package server.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import server.domain.TopItem;
import server.domain.UserProfile;

@Service
public class TopService {

    @Value("${top.list.quantity}")
    private int topListQuantity;

    private List<TopItem> topList;

    @Resource
    private TopDao topDao;

    public void onRatingChange(UserProfile profile){
        if (topList == null || topList.isEmpty()) {
            getTopList();
        }

        int min = topList.get(topList.size() - 1).rating;
        if (profile.getRating() > min) {
            getTopList();
        }

    }

    public List<TopItem> getTopList(){
        topList = topDao.getUsersSortedByRating(topListQuantity);
        return topList;
    }

}
