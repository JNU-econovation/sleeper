package econo.app.sleeper.web.sleep;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class SetTimeResponse {

    private final ZonedDateTime setSleepTime;
    private final ZonedDateTime setWakeTime;

    public static SetTimeResponse of(ZonedDateTime setSleepTime, ZonedDateTime setWakeTime){
        return SetTimeResponse.builder()
                .setSleepTime(setSleepTime)
                .setWakeTime(setWakeTime)
                .build();
    }
}
