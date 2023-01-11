package econo.app.sleeper.web.sleep;

import econo.app.sleeper.domain.sleep.Sleep;
import econo.app.sleeper.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
public class SetTimeDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime sleepTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime wakeTime;

    private String userId;

    public Sleep toEntity(User user){
        return Sleep.builder()
                .setSleepTime(ZonedDateTime.of(sleepTime, ZoneId.of("Asia/Seoul")))
                .setWakeTime(ZonedDateTime.of(wakeTime, ZoneId.of("Asia/Seoul")))
                .user(user)
                .build();
    }


}
