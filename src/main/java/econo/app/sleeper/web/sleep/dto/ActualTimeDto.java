package econo.app.sleeper.web.sleep.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class ActualTimeDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime actualSleepTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime actualWakeTime;

    public static ActualTimeDto of(LocalDateTime actualSleepTime, LocalDateTime actualWakeTime){
        return ActualTimeDto.builder()
                .actualSleepTime(actualSleepTime)
                .actualWakeTime(actualWakeTime)
                .build();
    }

}
