package econo.app.sleeper.web.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@Builder
public class LoginUserResponse implements Serializable{

    private String message;
    private Long sleepPk;
    private Long userPk;
    public static LoginUserResponse of (String message, Long sleepPk, Long userPk){
        return LoginUserResponse.builder()
                .sleepPk(sleepPk)
                .message(message)
                .userPk(userPk)
                .build();
    }



}
