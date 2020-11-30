package server.service;

import common.messages.EnterAccount;
import common.messages.Login;
import common.messages.LoginError;
import common.util.KeyValue;
import org.springframework.stereotype.Service;
import platform.service.LoginController;
import platform.service.LoginValidator;
import server.domain.UserProfile;

import java.util.Collections;
import java.util.List;

@Service
class LoginService implements LoginValidator<Login>, LoginController<Login, UserProfile> {

    @Override
    public boolean validate(Login login, KeyValue<Integer, String> error) {
        if (login.authToken.equals("ValidToken")) {
            return true;
        } else {
            error.key = 1;
            error.value = "InvalidToken";
            return false;
        }
    }

    @Override
    public List<Object> onSuccessLogin(UserProfile profile) {
        return Collections.singletonList(new EnterAccount(profile.serialize(), System.currentTimeMillis()));
    }

    @Override
    public Object onLoginError(Login login, KeyValue<Integer, String> error) {
        return new LoginError(error.key, error.value);
    }

}
