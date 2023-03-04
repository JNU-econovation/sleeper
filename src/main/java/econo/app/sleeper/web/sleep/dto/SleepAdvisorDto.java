package econo.app.sleeper.web.sleep.dto;

import econo.app.sleeper.domain.sleep.SleepAdvisor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Builder
@Getter
@RequiredArgsConstructor
public class SleepAdvisorDto {

    private final Long sleepAdvisorPk;
    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime goalSleepTime;
    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime goalWakeTime;

    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime minimumSleepTime;

    public SleepAdvisor toEntity(){
        return SleepAdvisor.builder()
                .goalSleepTime(goalSleepTime)
                .goalWakeTime(goalWakeTime)
                .minimumSleepTime(minimumSleepTime)
                .build();
    }


    public static SleepAdvisorDto of(Long sleepAdvisorPk,LocalTime goalSleepTime, LocalTime goalWakeTime, LocalTime minimumSleepTime){
        return SleepAdvisorDto.builder()
                .sleepAdvisorPk(sleepAdvisorPk)
                .goalSleepTime(goalSleepTime)
                .goalWakeTime(goalWakeTime)
                .minimumSleepTime(minimumSleepTime)
                .build();
    }

}
