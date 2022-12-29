package econo.app.sleeper.web.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
public class GoalTImeResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalTime goalSleepTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalTime goalWakeTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private final List<LocalTime> suggestedWakeTimes;

    public GoalTImeResponse toDto(LocalTime goalSleepTime, LocalTime goalWakeTime, List<LocalTime> suggestedWakeTimes){
        return GoalTImeResponse.builder()
                .goalSleepTime(goalSleepTime)
                .goalSleepTime(goalWakeTime)
                .suggestedWakeTimes(suggestedWakeTimes)
                .build();
    }
}
