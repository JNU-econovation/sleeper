package econo.app.sleeper.web.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
public class GoalTimeResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalTime goalSleepTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalTime goalWakeTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private final List<LocalTime> suggestedWakeTimes;

    public static GoalTimeResponse of(LocalTime goalSleepTime, LocalTime goalWakeTime, List<LocalTime> suggestedWakeTimes){
        return GoalTimeResponse.builder()
                .goalSleepTime(goalSleepTime)
                .goalWakeTime(goalWakeTime)
                .suggestedWakeTimes(suggestedWakeTimes)
                .build();
    }
}
