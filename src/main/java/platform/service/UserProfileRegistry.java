package platform.service;

import platform.domain.IUser;

public interface UserProfileRegistry {

    IUser createNewUserProfile(String uid);

    IUser findUserProfileByUid(String uid);

    IUser selectUserProfile(int profileId);

    void updateUserProfile(IUser userProfile);
}
