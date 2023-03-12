package econo.app.sleeper.web.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;


@Builder
@Getter
@RequiredArgsConstructor
public class WakeTimeRecommendDto {

    private final Long sleepAdvisorPk;

    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime expectedSleepTime;


    public static WakeTimeRecommendDto of(Long userSleepId, LocalTime expectedSleepTime){
        return WakeTimeRecommendDto.builder()
                .sleepAdvisorPk(userSleepId)
                .expectedSleepTime(expectedSleepTime)
                .build();
    }
}
