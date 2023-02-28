package econo.app.sleeper.web.user.dto;

import econo.app.sleeper.domain.user.RoleType;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.domain.sleep.SleepAdvisor;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalTime;

@Data
public class SignUpRequest {

    private String userId;
    private String userPassword;
    private String userNickName;
    private Long userAge;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime goalSleepTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime goalWakeTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime minimumSleepTime;

    public User toUserEntity(){
        return User.builder()
                .userId(userId)
                .userPassword(userPassword)
                .userNickName(userNickName)
                .userAge(userAge)
                .roleType(RoleType.USER)
                .build();
    }

    public SleepAdvisor toSleepAdvisor(){
        return SleepAdvisor.builder()
                .goalSleepTime(goalSleepTime)
                .goalWakeTime(goalWakeTime)
                .minimumSleepTime(minimumSleepTime)
                .build();
    }


}


