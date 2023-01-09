package econo.app.sleeper.web.sleep;

import econo.app.sleeper.domain.Sleep.Sleep;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class SetTimeRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime setSleepTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime setWakeTime;


    public static Sleep toEntity(ZonedDateTime setSleepTime, ZonedDateTime setWakeTime){
        return Sleep.builder()
                .setSleepTime(setSleepTime)
                .setWakeTime(setWakeTime)
                .build();
    }

    public static SetTimeRequest of(LocalDateTime setSleepTime, LocalDateTime setWakeTime){
        return SetTimeRequest.builder()
                .setSleepTime(setSleepTime)
                .setWakeTime(setWakeTime)
                .build();
    }
}

