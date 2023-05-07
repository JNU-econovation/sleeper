package econo.app.sleeper.web.sleep.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
public class SleepRequest {

    @NotNull
    @JsonProperty("setSleepTime")
    private final ZonedDateTime setSleepTime;
    @NotNull
    @JsonProperty("setWakeTime")
    private final ZonedDateTime setWakeTime;

    @NotNull
    @JsonProperty("actualSleepTime")
    private final ZonedDateTime actualSleepTime;

    @NotNull
    private final Long characterPk;

    @NotNull
    private final Long userPk;

}
