package common.messages;

import platform.messages.ILogin;

import java.util.Collections;
import java.util.Map;

public class Login implements ILogin {

    public String uid;

    public String authToken;

    public Map<String, String> params = Collections.emptyMap();

    public Login() {
    }

    public Login(String uid, String authToken) {
        this.uid = uid;
        this.authToken = authToken;
    }

    public String uid() {
        return uid;
    }

    @Override
    public String toString() {
        return "Login{" +
                "uid='" + uid + '\'' +
                ", authToken='" + authToken + '\'' +
                ", params=" + params +
                '}';
    }

}
