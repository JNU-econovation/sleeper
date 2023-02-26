package econo.app.sleeper.web.sleep;

import econo.app.sleeper.domain.sleep.SleepAdvisor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Builder
@Getter
@RequiredArgsConstructor
public class SleepAdvisorResponse {

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime goalSleepTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime goalWakeTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime minimumSleepTime;

    public static SleepAdvisorResponse of(LocalTime goalSleepTime, LocalTime goalWakeTime, LocalTime minimumSleepTime){
        return SleepAdvisorResponse.builder()
                .goalSleepTime(goalSleepTime)
                .goalWakeTime(goalWakeTime)
                .minimumSleepTime(minimumSleepTime)
                .build();
    }
}
