package econo.app.sleeper.web.sleep.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class SleepDto {

    private Long sleepPk;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private ZonedDateTime actualWakeTime;

    public static SleepDto of(Long sleepPk, ZonedDateTime actualRequest){
        return SleepDto.builder()
                .sleepPk(sleepPk)
                .actualWakeTime(actualRequest)
                .build();
    }
}
