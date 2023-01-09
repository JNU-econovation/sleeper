package econo.app.sleeper.web.user;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
@Builder
public class GoalTimeDto {

    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime goalSleepTime;

    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime goalWakeTime;

    private final String userId;

    public static GoalTimeDto of(LocalTime goalSleepTime, LocalTime getWakeTIme, String userId){
        return GoalTimeDto.builder()
                .goalSleepTime(goalSleepTime)
                .goalWakeTime(getWakeTIme)
                .userId(userId)
                .build();
    }
}
