package server;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ServerApplication.class)
public class UserDefaultPropertiesTest {

  @Value("#{defaultUserProperties}")
  private Map<String, String> defaultUserProperties;

  @Test
  public void checkDefaultUserTest() {
    assertThat("", equalToIgnoringCase(defaultUserProperties.get("name")));
    assertThat("1", equalToIgnoringCase(defaultUserProperties.get("level")));
    assertThat("0", equalToIgnoringCase(defaultUserProperties.get("experience")));
    assertThat("25", equalToIgnoringCase(defaultUserProperties.get("energy")));
    assertThat("0", equalToIgnoringCase(defaultUserProperties.get("rating")));
    assertThat("100", equalToIgnoringCase(defaultUserProperties.get("money")));
  }

}
