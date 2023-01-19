package econo.app.sleeper.web.sleep;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
