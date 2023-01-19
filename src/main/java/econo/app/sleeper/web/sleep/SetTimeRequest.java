package econo.app.sleeper.web.sleep;

import com.fasterxml.jackson.annotation.JsonProperty;
import econo.app.sleeper.domain.sleep.Sleep;
import econo.app.sleeper.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
public class SetTimeRequest {

    @NotNull
    @JsonProperty("setSleepTime")
    private ZonedDateTime setSleepTime;
    @NotNull
    @JsonProperty("setWakeTime")
    private ZonedDateTime setWakeTime;

    @NotNull
    private Long userPk;

    public Sleep toEntity(User user) {
        return Sleep.builder()
                .setSleepTime(setSleepTime)
                .setWakeTime(setWakeTime)
                .user(user)
                .build();
    }
}
