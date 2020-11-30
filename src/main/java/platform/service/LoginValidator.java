package platform.service;

import platform.messages.ILogin;
import common.util.KeyValue;

public interface LoginValidator<LOGIN extends ILogin> {

    boolean validate(LOGIN login, KeyValue<Integer, String> error);

}
