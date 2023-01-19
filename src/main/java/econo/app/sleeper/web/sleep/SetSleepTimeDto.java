package econo.app.sleeper.web.sleep;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class SetSleepTimeDto {

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime setSleepTime;
}
