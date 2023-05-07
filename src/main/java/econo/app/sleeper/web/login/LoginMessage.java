package econo.app.sleeper.web.login;

public enum LoginMessage {

    issue_AccessToken_RefreshToken("accessToken과 refreshToken을 발급"),
    re_request_with_AccessToken_RefreshToken("accessToken과 refreshToken과 함께 재요청");

    private final String message;

    LoginMessage(String message) {
        this.message = message;
    }
}
