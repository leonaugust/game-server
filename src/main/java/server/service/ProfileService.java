package server.service;

import common.dto.AwardStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import platform.service.UserProfileRegistry;
import server.domain.UserProfile;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class ProfileService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserProfileRegistry userProfileRegistry;

    @Value("#{levelsConfig}")
    private Map<Integer, Integer> levelsConfig;

    @Value("#{levelUpAwardConfig}")
    private Map<Integer, AwardStructure> levelUpAwardConfig;

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
