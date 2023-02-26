package econo.app.sleeper.web.user;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;


@Builder
@Getter
@RequiredArgsConstructor
public class WakeTimeRecommendDto {

    private Long sleepAdvisorPk;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime expectedSleepTime;


    public static WakeTimeRecommendDto of(Long userSleepId, LocalTime expectedSleepTime){
        return WakeTimeRecommendDto.builder()
                .sleepAdvisorPk(userSleepId)
                .expectedSleepTime(expectedSleepTime)
                .build();
    }
}
