package econo.app.sleeper.web.member.dto;

import econo.app.sleeper.domain.member.Member;
import econo.app.sleeper.domain.member.RoleType;
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


}


