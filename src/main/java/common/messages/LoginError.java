package common.messages;

public class LoginError extends AbstractResponse {

    public LoginError() {
    }

    public LoginError(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    @Override
    public String toString() {
        return "LoginError{" +
                "errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

}
