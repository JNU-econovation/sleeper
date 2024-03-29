package econo.app.sleeper.domain.sleep;

import econo.app.sleeper.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER_SLEEP_INFO")
public class SleepAdvisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_GOAL_SLEEP_TIME", columnDefinition = "TIME")
    private LocalTime goalSleepTime;

    @Column(name = "USER_GOAL_WAKE_TIME", columnDefinition = "TIME")
    private LocalTime goalWakeTime;

    @Column(name = "USER_MINIMUM_SLEEP_TIME", columnDefinition = "TIME")
    private LocalTime minimumSleepTime;

    @Builder
    public SleepAdvisor(LocalTime goalSleepTime, LocalTime goalWakeTime, LocalTime minimumSleepTime) {
        this.goalSleepTime = goalSleepTime;
        this.goalWakeTime = goalWakeTime;
        this.minimumSleepTime = minimumSleepTime;
    }

    public List<LocalTime> recommendWakeTimes(LocalTime expectedSleepTime){

        Integer recommendWakeTimeCount = 4;

        List<LocalTime> wakeTimes = new ArrayList<>();
        LocalTime baseWakeTime = expectedSleepTime.plusHours(minimumSleepTime.getHour()).plusMinutes(minimumSleepTime.getMinute());
        for(int i = 0; i < recommendWakeTimeCount; i++) {
            LocalTime plusWakeTime = baseWakeTime.plusHours(1).plusMinutes(30);
            wakeTimes.add(plusWakeTime);
        }
        Stream<LocalTime> recommendWakeTimes = wakeTimes.stream().filter(recommendWakeTime -> recommendWakeTime.isBefore(goalWakeTime));
        List<LocalTime> times = recommendWakeTimes.collect(Collectors.toList());
        if(times.isEmpty()){
            times.add(goalWakeTime);
        }
        return times;
    }

    public void updateSleepAdvisor(LocalTime goalSleepTime, LocalTime goalWakeTime, LocalTime minimumSleepTime){
        this.goalSleepTime = goalSleepTime;
        this.goalWakeTime = goalWakeTime;
        this.minimumSleepTime = minimumSleepTime;
    }

    public static SleepAdvisor createSleepAdvisor(LocalTime goalSleepTime, LocalTime goalWakeTime, LocalTime minimumSleepTime, Member member){
        SleepAdvisor sleepAdvisor = new SleepAdvisor(goalSleepTime,goalWakeTime,minimumSleepTime);
        member.mappingSleepAdvisor(sleepAdvisor);
        return sleepAdvisor;
    }

}
