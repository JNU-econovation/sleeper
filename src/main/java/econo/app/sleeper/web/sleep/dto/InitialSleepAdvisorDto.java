package econo.app.sleeper.web.sleep.dto;

import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.web.character.dto.InitialCharacterDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
@Builder
public class InitialSleepAdvisorDto {


    private final User user;

    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime goalSleepTime;

    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime goalWakeTime;

    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime minimumSleepTime;

    public static InitialSleepAdvisorDto of(User user, LocalTime goalSleepTime, LocalTime goalWakeTime, LocalTime minimumSleepTime){
        return InitialSleepAdvisorDto.builder()
                .user(user)
                .goalSleepTime(goalSleepTime)
                .goalWakeTime(goalWakeTime)
                .minimumSleepTime(minimumSleepTime)
                .build();
    }

}
