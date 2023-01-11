package econo.app.sleeper.web.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class GoalTimeRequest {

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime goalSleepTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime goalWakeTime;

    private String userId;

}
