package econo.app.sleeper.web.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@Builder
public class LoginTokenDto implements Serializable{

    private String message;
    private String accessToken;
    private String refreshToken;

    public static LoginTokenDto of (String message, String accessToken, String refreshToken){
        return LoginTokenDto.builder()
                .message(message)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }



}