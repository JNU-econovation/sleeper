package econo.app.sleeper.web.sleep;

import econo.app.sleeper.domain.Sleep;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class TimeRequestDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime sleepTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime wakeTime;

    public static Sleep toEntity(ZonedDateTime sleepTime, ZonedDateTime wakeTime){
        return Sleep.builder()
                .setSleepTime(sleepTime)
                .setWakeTime(wakeTime)
                .build();
    }

    public static TimeRequestDto toDto(LocalDateTime sleepTime,LocalDateTime wakeTime){
        return TimeRequestDto.builder()
                .sleepTime(sleepTime)
                .wakeTime(wakeTime)
                .build();
    }
}

