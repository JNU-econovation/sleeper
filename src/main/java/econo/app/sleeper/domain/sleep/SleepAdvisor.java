package econo.app.sleeper.domain.sleep;

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
    @GeneratedValue
    private Long id;

    private final Integer recommendWakeTimeCount = 4;

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

        List<LocalTime> wakeTimes = new ArrayList<>();
        LocalTime baseWakeTime = expectedSleepTime.plusHours(minimumSleepTime.getHour()).plusMinutes(minimumSleepTime.getMinute());

        for(int i = 0; i < recommendWakeTimeCount; i++) {
            LocalTime plusWakeTime = baseWakeTime.plusHours(1).plusMinutes(30);
            wakeTimes.add(plusWakeTime);
        }
        Stream<LocalTime> recommendWakeTimes = wakeTimes.stream().filter(recommendWakeTime -> recommendWakeTime.isBefore(goalWakeTime));
        return recommendWakeTimes.collect(Collectors.toList());
    }

    public void updateGoalSleepTime(LocalTime goalSleepTime){
        this.goalSleepTime = goalSleepTime;
    }

    public void updateGoalWakeTime(LocalTime goalWakeTime){
        this.goalWakeTime = goalWakeTime;
    }

    public void updateMinimumSleepTime(LocalTime minimumSleepTime){
        this.minimumSleepTime = minimumSleepTime;
    }

    public static SleepAdvisor createSleepAdvisor(LocalTime goalSleepTime, LocalTime goalWakeTime, LocalTime minimumSleepTime){
        SleepAdvisor sleepAdvisor = new SleepAdvisor(goalSleepTime,goalWakeTime,minimumSleepTime);
        return sleepAdvisor;
    }

}
