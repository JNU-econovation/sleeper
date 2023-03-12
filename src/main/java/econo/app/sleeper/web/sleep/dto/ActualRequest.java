package econo.app.sleeper.web.sleep.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
public class ActualRequest {

    @NotNull
    @JsonProperty("actualWakeTime")
    private final ZonedDateTime actualWakeTime;

    @NotNull
    private final Long characterPk;

}
