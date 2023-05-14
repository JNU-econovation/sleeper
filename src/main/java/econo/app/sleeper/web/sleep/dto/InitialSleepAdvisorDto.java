package econo.app.sleeper.web.sleep.dto;

import econo.app.sleeper.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
@Builder
public class InitialSleepAdvisorDto {


    private final Member member;

    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime goalSleepTime;

    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime goalWakeTime;

    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime minimumSleepTime;

    public static InitialSleepAdvisorDto of(Member member, LocalTime goalSleepTime, LocalTime goalWakeTime, LocalTime minimumSleepTime){
        return InitialSleepAdvisorDto.builder()
                .member(member)
                .goalSleepTime(goalSleepTime)
                .goalWakeTime(goalWakeTime)
                .minimumSleepTime(minimumSleepTime)
                .build();
    }

}
