package econo.app.sleeper.web.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@Builder
public class LoginResponse implements Serializable{

    private Long characterPk;
    private Long userPk;
    private Long sleepAdvisorPk;
    private String message;

    public static LoginResponse of (Long characterPk, Long userPk, Long userSleepPk, String message){
        return LoginResponse.builder()
                .characterPk(characterPk)
                .userPk(userPk)
                .sleepAdvisorPk(userSleepPk)
                .message(message)
                .build();
    }



}
