
package platform.service;

import platform.messages.ILogin;
import common.util.KeyValue;
import platform.domain.IUser;

import java.util.*;

public interface LoginController<LOGIN extends ILogin, USER_PROFILE extends IUser> {

    List<Object> onSuccessLogin(USER_PROFILE profile);

    Object onLoginError(LOGIN message, KeyValue<Integer, String> error);

}
