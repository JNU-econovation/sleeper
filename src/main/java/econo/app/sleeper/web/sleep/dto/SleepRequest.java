package econo.app.sleeper.web.sleep.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import econo.app.sleeper.domain.sleep.Sleep;
import econo.app.sleeper.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
public class SleepRequest {

    @NotNull
    @JsonProperty("setSleepTime")
    private ZonedDateTime setSleepTime;
    @NotNull
    @JsonProperty("setWakeTime")
    private ZonedDateTime setWakeTime;

    @NotNull
    @JsonProperty("actualSleepTime")
    private ZonedDateTime actualSleepTime;

    @NotNull
    private Long characterPk;

}
