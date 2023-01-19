package econo.app.sleeper.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
@NoArgsConstructor
public class GoalTime {

    @Column(name = "USER_GOAL_SLEEP_TIME", columnDefinition = "TIME")
    private LocalTime goalSleepTime;

    @Column(name = "USER_GOAL_WAKE_TIME", columnDefinition = "TIME")
    private LocalTime goalWakeTime;

    public GoalTime(LocalTime goalSleepTime, LocalTime goalWakeTime){
        this.goalSleepTime = goalSleepTime;
        this.goalWakeTime = goalWakeTime;
    }


    public List<ZonedDateTime> toLocalDateTime(){

        LocalDate savingDate = LocalDate.now(ZoneId.of("Asia/Seoul"));

        // 목표 수면 시간이 5시~23시 59이면 savingDate 걍 쓰면 됨, 목표 기상시간은 savingDate +1 해야함
        if(goalSleepTime.isAfter(LocalTime.of(5,0)) & goalSleepTime.isBefore(LocalTime.of(23,59))){
            List<ZonedDateTime> zonedDateTimes = new ArrayList<>();
            zonedDateTimes.add(ZonedDateTime.of(LocalDateTime.of(savingDate,goalSleepTime),ZoneId.of("Asia/Seoul")));
            zonedDateTimes.add(ZonedDateTime.of(LocalDateTime.of(savingDate.plusDays(1L),goalWakeTime),ZoneId.of("Asia/Seoul")));
            return zonedDateTimes;
        }
        List<ZonedDateTime> zonedDateTimes = new ArrayList<>();
        zonedDateTimes.add(ZonedDateTime.of(LocalDateTime.of(savingDate.plusDays(1L),goalSleepTime),ZoneId.of("Asia/Seoul")));
        zonedDateTimes.add(ZonedDateTime.of(LocalDateTime.of(savingDate.plusDays(1L),goalWakeTime),ZoneId.of("Asia/Seoul")));
        return zonedDateTimes;
        // 목표 수면 시간이 0시~5시이면 savingDate + 1 해야함, 목표 기상시간은 savingDate +1 해야함

    }

}
