package econo.app.sleeper.web.sleep;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class SetTimeResponse {

    private final LocalTime setSleepTime;
    private final LocalTime setWakeTime;

    public static SetTimeResponse of(ZonedDateTime setSleepTime, ZonedDateTime setWakeTime){
        return SetTimeResponse.builder()
                .setSleepTime(setSleepTime.toLocalTime())
                .setWakeTime(setWakeTime.toLocalTime())
                .build();
    }
}