package client;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import platform.service.UserProfileRegistry;
import server.ServerApplication;
import server.domain.TopItem;
import server.domain.UserProfile;
import server.service.ProfileService;
import server.service.TopService;

@SpringBootTest(classes = ServerApplication.class)
public class TopRequestTest extends ConnectAndLoginTests {

  @Resource
  private TopService topService;

  @Resource
  private ProfileService profileService;

  @Resource
  private UserProfileRegistry registry;

  @Value("${top.list.quantity}")
  private int topListQuantity;

  private UserProfile profile;

  @Test
  public void getTopListTest() {
    successLoginTest();

    IntStream.rangeClosed(1, 15).forEach(i -> {
      UserProfile user = (UserProfile) registry.createNewUserProfile(UUID.randomUUID().toString());
      user.setRating(i);
      registry.updateUserProfile(user);
    });

    List<TopItem> topList = topService.getTopList();
    assertSame(topListQuantity, topList.size());
  }

  @Test
  public void onRatingChangeNewRatingLeaderTest() {
    successLoginTest();
    profile = profileService.selectUserProfile(enterAccount.userProfile.id);

    IntStream.rangeClosed(1, 15).forEach(i -> {
      UserProfile user = (UserProfile) registry.createNewUserProfile(UUID.randomUUID().toString());
      user.setRating(i);
      registry.updateUserProfile(user);
    });

    List<TopItem> topList = topService.getTopList();
    assertSame(topListQuantity, topList.size());
    assertSame(15, topList.get(0).rating);

    profile.setRating(100);
    registry.updateUserProfile(profile);
    topService.onRatingChange(profile);
    topList = topService.getTopList();
    assertSame(100, topList.get(0).rating);
  }

}
