package client;

import static org.junit.jupiter.api.Assertions.assertSame;

import common.dto.AwardStructure;
import common.messages.StartGameRequest;
import common.messages.StartGameResponse;
import java.util.stream.IntStream;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import server.ServerApplication;
import server.domain.UserProfile;
import server.service.ProfileService;

@SpringBootTest(classes = ServerApplication.class)
public class ProfileTests extends ConnectAndLoginTests {

    @Resource
    ProfileService profileService;

    private UserProfile profile;

    @Test
    public void userReachedNewLevelTest() {
        successLoginTest();
        profile = profileService.selectUserProfile(enterAccount.userProfile.id);

        AwardStructure award = profileService.handleLevelUpCase(profile, 25);
        assertSame(100, award.getMoney());
        assertSame(100, award.getEnergy());
        assertSame(5, profile.getExperience());
        assertSame(2, profile.getLevel());
    }

    @Test
    public void userHasNotEnoughExperience() {
        successLoginTest();
        profile = profileService.selectUserProfile(enterAccount.userProfile.id);

        AwardStructure award = profileService.handleLevelUpCase(profile, 19);
        assertSame(0, award.getMoney());
        assertSame(0, award.getEnergy());
        assertSame(19, profile.getExperience());
        assertSame(1, profile.getLevel());
    }

    @Test
    public void withdrawEnergyByStartGameTest() {
        successLoginTest();
        profile = profileService.selectUserProfile(enterAccount.userProfile.id);

        assertSame(25, profile.getEnergy());
        assertSame(100, profile.getMoney());

        IntStream.rangeClosed(1, 5).forEach(i -> {
            var startGameResponse = request(new StartGameRequest(), StartGameResponse.class);
            assertSame(0, startGameResponse.errorCode);
        });
        var startGameResponse = request(new StartGameRequest(), StartGameResponse.class);
        assertSame(1, startGameResponse.errorCode);
    }

    // mock method
    public StartGameResponse request(StartGameRequest message, Class<StartGameResponse> responseClass) {
        if (profile.getEnergy() >= 5) {
            profile.setEnergy(profile.getEnergy() - 5);

            return new StartGameResponse();
        } else {
            var startGameResponse = new StartGameResponse();
            startGameResponse.errorCode = 1;
            startGameResponse.errorMessage = "No enough energy!";

            return startGameResponse;
        }
    }

}
