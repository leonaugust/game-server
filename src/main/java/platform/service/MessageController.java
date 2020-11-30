package platform.service;

import platform.domain.IUser;

public interface MessageController<MESSAGE, USER_PROFILE extends IUser> {

    Object onMessage(MESSAGE message, USER_PROFILE profile);

    Class<MESSAGE> messageClass();

}
