package common.messages;

import common.dto.UserProfileStructure;

public class EnterAccount {

    public UserProfileStructure userProfile;

    public long serverTime;

    public EnterAccount() {
    }

    public EnterAccount(UserProfileStructure userProfile, long serverTime) {
        this.userProfile = userProfile;
        this.serverTime = serverTime;
    }

    @Override
    public String toString() {
        return "EnterAccount{" +
                "userProfile=" + userProfile +
                ", serverTime=" + serverTime +
                '}';
    }

}
