package econo.app.sleeper.domain.sleep;


import econo.app.sleeper.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SLEEP")
public class Sleep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP")
    private ZonedDateTime setSleepTime;

    @Column(columnDefinition = "TIMESTAMP")
    private ZonedDateTime setWakeTime;

    @Column(columnDefinition = "TIMESTAMP")
    private ZonedDateTime actualSleepTime;
    @Column(columnDefinition = "TIMESTAMP")
    private ZonedDateTime actualWakeTime;

    @Column
    private LocalDate sleepDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_FK")
    private User user;

    @Builder
    public Sleep(ZonedDateTime setSleepTime, ZonedDateTime setWakeTime, ZonedDateTime actualSleepTime, LocalDate sleepDate){
        this.setSleepTime = setSleepTime;
        this.setWakeTime = setWakeTime;
        this.actualSleepTime = actualSleepTime;
        this.sleepDate = sleepDate;
    }

    private void mappingUser(User user){
        this.user = user;
    }

    public static Sleep createSleep(ZonedDateTime setSleepTime, ZonedDateTime setWakeTime, ZonedDateTime actualSleepTime, LocalDate sleepDate, User user){
        Sleep sleep = new Sleep();
        sleep.setSleepTime = setSleepTime;
        sleep.setWakeTime = setWakeTime;
        sleep.actualSleepTime = actualSleepTime;
        sleep.sleepDate = sleepDate;
        sleep.mappingUser(user);
        return sleep;
    }

    public void updateActualWakeTime(ZonedDateTime actualWakeTime){
        this.actualWakeTime = actualWakeTime;
    }

    public Integer calculateXp() {
        System.out.println(" = ");
        if(actualSleepTime.isAfter(setWakeTime)){
            return 0;
        } else if(actualWakeTime.isBefore(setSleepTime)){
            return 0;
        } else if(actualWakeTime.isAfter(setSleepTime) & actualWakeTime.isBefore(setWakeTime)){
            long between = ChronoUnit.HOURS.between(setSleepTime,actualWakeTime);
            return (int)between;
        } else if(actualSleepTime.isBefore(setSleepTime) & actualWakeTime.isAfter(setWakeTime)){
            long between = ChronoUnit.HOURS.between(setSleepTime, setWakeTime);
            return (int)between;
        } else if (actualSleepTime.isAfter(setSleepTime) & actualSleepTime.isBefore(setWakeTime)) {
            long between = ChronoUnit.HOURS.between(actualSleepTime, setWakeTime);
            return (int)between;
        } else if (actualWakeTime.isBefore(setSleepTime) & actualWakeTime.isBefore(setWakeTime)) {
            long between = ChronoUnit.HOURS.between(setSleepTime, actualWakeTime);
            return (int)between;
        }
        return 0;
    }

    public Long evaluateSleep(){
        LocalTime goalSleepTime = user.getSleepAdvisor().getGoalSleepTime();
        LocalTime goalWakeTime = user.getSleepAdvisor().getGoalWakeTime();
        Long denominator = ChronoUnit.HOURS.between(goalWakeTime, goalSleepTime);
        LocalTime actualSleepTime = this.actualSleepTime.toLocalTime();
        LocalTime actualWakeTime = this.actualWakeTime.toLocalTime();
        Long score = 0L;
        if(goalSleepTime.isAfter(actualWakeTime)){
            return score;
        } else if(goalWakeTime.isBefore(actualSleepTime)){
            return score;
        } else if(goalWakeTime.isAfter(actualSleepTime) & goalWakeTime.isBefore(actualWakeTime)){
            long between = ChronoUnit.HOURS.between(actualSleepTime,goalWakeTime);
            score = between/denominator;
            return score;
        } else if(goalSleepTime.isBefore(actualSleepTime) & goalWakeTime.isAfter(actualWakeTime)){
            long between = ChronoUnit.HOURS.between(actualSleepTime, actualWakeTime);
            score = between/denominator;
            return score;
        } else if (goalSleepTime.isAfter(actualSleepTime) & goalSleepTime.isBefore(actualWakeTime)) {
            long between = ChronoUnit.HOURS.between(goalSleepTime, actualWakeTime);
            score = between/denominator;
            return score;
        } else if (goalWakeTime.isBefore(actualSleepTime) & goalWakeTime.isBefore(actualWakeTime)) {
            long between = ChronoUnit.HOURS.between(actualSleepTime, goalWakeTime);
            score = between/denominator;
            return score;
        }
        return 0L;

    }

    public List<ZonedDateTime> getSleepInfo(){
        List<ZonedDateTime> sleepInfo = new ArrayList<>();
        sleepInfo.add(setSleepTime);
        sleepInfo.add(setWakeTime);
        sleepInfo.add(actualSleepTime);
        sleepInfo.add(actualWakeTime);
        return sleepInfo;
    }



}
