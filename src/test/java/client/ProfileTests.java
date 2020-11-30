package client;

import common.messages.StartGameRequest;
import common.messages.StartGameResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import server.ServerApplication;
import server.domain.UserProfile;
import server.service.ProfileService;

import javax.annotation.Resource;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest(classes = ServerApplication.class)
public class ProfileTests extends ConnectAndLoginTests {

    @Resource
    ProfileService profileService;

    private UserProfile profile;

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
