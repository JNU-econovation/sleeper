package econo.app.sleeper.web.sleep;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
public class ActualRequest {

    @NotNull
    @JsonProperty("actualWakeTime")
    private ZonedDateTime actualWakeTime;

    @NotNull
    private Long userPk;

}
