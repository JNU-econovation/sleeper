package econo.app.sleeper.web.login;

import econo.app.sleeper.domain.user.User;
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
    private Long userPk;
    public static LoginResponse of (String accessToken, String refreshToken, String message,Long userPk){
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .message(message)
                .userPk(userPk)
                .build();
    }



}
