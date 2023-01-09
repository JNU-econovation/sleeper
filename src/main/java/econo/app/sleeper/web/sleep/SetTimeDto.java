package econo.app.sleeper.web.sleep;

import econo.app.sleeper.domain.Sleep.Sleep;
import econo.app.sleeper.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class SetTimeDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime sleepTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime wakeTime;

    private final String userId;

    public static SetTimeDto of(LocalDateTime sleepTime, LocalDateTime wakeTime, String userId){
        return SetTimeDto.builder()
                .sleepTime(sleepTime)
                .wakeTime(wakeTime)
                .userId(userId)
                .build();
    }

    public Sleep toEntity(User user){
        return Sleep.builder()
                .setSleepTime(ZonedDateTime.of(sleepTime, ZoneId.of("Asia/Seoul")))
                .setWakeTime(ZonedDateTime.of(wakeTime, ZoneId.of("Asia/Seoul")))
                .user(user)
                .build();
    }
}
