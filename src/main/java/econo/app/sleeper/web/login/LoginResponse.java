package econo.app.sleeper.web.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@Builder
public class LoginResponse implements Serializable{

    private String message;
    private String accessToken;
    private String refreshToken;

    public static LoginResponse of (String message, String accessToken, String refreshToken){
        return LoginResponse.builder()
                .message(message)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }



}