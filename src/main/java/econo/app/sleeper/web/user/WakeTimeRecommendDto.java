package econo.app.sleeper.web.user;

import econo.app.sleeper.web.sleep.SleepCharacterDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;


@Builder
@Getter
@RequiredArgsConstructor
public class WakeTimeRecommendDto {

    private Long userSleepId;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime expectedSleepTime;


    public static WakeTimeRecommendDto of(Long userSleepId, LocalTime expectedSleepTime){
        return WakeTimeRecommendDto.builder()
                .userSleepId(userSleepId)
                .expectedSleepTime(expectedSleepTime)
                .build();
    }
}
