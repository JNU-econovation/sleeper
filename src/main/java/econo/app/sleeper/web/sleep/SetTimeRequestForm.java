package econo.app.sleeper.web.sleep;

import econo.app.sleeper.domain.Sleep;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class SetTimeRequestForm {

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
}

