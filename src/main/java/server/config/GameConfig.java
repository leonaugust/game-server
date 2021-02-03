package server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {

  @Value("${energy.price.for.game}")
  private int energyPriceForGame;

  @Value("${experience.reward.win}")
  private int experienceRewardWin;
  @Value("${experience.reward.defeat}")
  private int experienceRewardDefeat;

  @Value("${rating.result.win}")
  private int ratingResultWin;
  @Value("${rating.result.defeat}")
  private int ratingResultDefeat;
  @Value("${rating.minimal}")
  private int ratingMinimal;

  @Value("${money.reward.win}")
  private int moneyRewardWin;

  public int getEnergyPriceForGame() {
    return energyPriceForGame;
  }

  public int getExperienceRewardWin() {
    return experienceRewardWin;
  }

  public int getExperienceRewardDefeat() {
    return experienceRewardDefeat;
  }

  public int getRatingResultWin() {
    return ratingResultWin;
  }

  public int getRatingResultDefeat() {
    return ratingResultDefeat;
  }

  public int getRatingMinimal() {
    return ratingMinimal;
  }

  public int getMoneyRewardWin() {
    return moneyRewardWin;
  }
}
