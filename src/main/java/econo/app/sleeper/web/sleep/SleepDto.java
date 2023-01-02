package econo.app.sleeper.web.sleep;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class SleepDto {

    private final String userId;
    private final Long sleepPk;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime actualWakeTime;

    public static SleepDto of(String userId, Long sleepPk, LocalDateTime actualRequest){
        return SleepDto.builder()
                .userId(userId)
                .sleepPk(sleepPk)
                .actualWakeTime(actualRequest)
                .build();
    }
}
