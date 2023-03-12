package econo.app.sleeper.web.user.dto;

import econo.app.sleeper.domain.user.RoleType;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.domain.sleep.SleepAdvisor;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalTime;

@Builder
@Getter
@RequiredArgsConstructor
public class SignUpRequest {

    private final String userId;
    private final String userPassword;
    private final String userNickName;
    private final Long userAge;

    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime goalSleepTime;

    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime goalWakeTime;

    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime minimumSleepTime;

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


