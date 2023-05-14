package econo.app.sleeper.web.member.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalTime;

@Builder
@Getter
@RequiredArgsConstructor
public class SignUpRequest {

    private final String memberId;
    private final String memberPassword;
    private final String memberNickName;
    private final Long memberAge;

    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime goalSleepTime;

    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime goalWakeTime;

    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime minimumSleepTime;


}


