package econo.app.sleeper.web.sleep.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Builder
@Getter
@RequiredArgsConstructor
public class SleepAdvisorRequest {

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime goalSleepTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime goalWakeTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime minimumSleepTime;
}
