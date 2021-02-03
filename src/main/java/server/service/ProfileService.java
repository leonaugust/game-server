package server.service;

import common.dto.AwardStructure;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import platform.service.UserProfileRegistry;
import server.domain.UserProfile;

@Service
public class ProfileService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserProfileRegistry userProfileRegistry;

    @Value("#{levelsConfig}")
    private Map<Integer, Integer> levelsConfig;

    @Value("#{levelUpAwardConfig}")
    private Map<Integer, AwardStructure> levelUpAwardConfig;

    public AwardStructure handleLevelUpCase(UserProfile profile, int currentExperience) {
        AwardStructure award = new AwardStructure();
        int nextLevel = profile.getLevel() + 1;
        int experienceForNextLevel = levelsConfig.get(nextLevel);
        if (currentExperience >= experienceForNextLevel) {
            profile.setExperience(currentExperience - experienceForNextLevel);
            profile.setLevel(nextLevel);
            int rewardMoney = levelUpAwardConfig.get(nextLevel).getMoney();
            int rewardEnergy = levelUpAwardConfig.get(nextLevel).getEnergy();
            award.setMoney(rewardMoney);
            award.setEnergy(rewardEnergy);
        } else {
            profile.setExperience(currentExperience);
        }
        return award;
    }

    public boolean changeUsername(UserProfile profile, String newUsername) {
        Instant nameChangedDate = profile.getNameChangedDate().toInstant()
            .truncatedTo(ChronoUnit.DAYS);
        Instant today = Instant.now()
            .truncatedTo(ChronoUnit.DAYS);
        if (nameChangedDate.equals(today)) {
            return false;
        }
        profile.setName(newUsername);
        profile.setNameChangedDate(Date.from(today));
        userProfileRegistry.updateUserProfile(profile);
        log.info("changeUsername for profile {}", profile.id);
        return true;
    }

    public UserProfile findUserProfileOrCreateNew(String uid){
        var profile = userProfileRegistry.findUserProfileByUid(uid);
        if(profile == null){
            profile = userProfileRegistry.createNewUserProfile(uid);
        }
        return (UserProfile) profile;
    }

    public UserProfile selectUserProfile(int profileId){
        return (UserProfile) userProfileRegistry.selectUserProfile(profileId);
    }

}
