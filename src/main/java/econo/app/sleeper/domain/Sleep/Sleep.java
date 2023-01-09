package econo.app.sleeper.domain.Sleep;


import econo.app.sleeper.domain.common.SavingDate;
import econo.app.sleeper.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SLEEP")
public class Sleep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sleepPk;

    @Embedded
    private SetTime setTime;
    @Column(columnDefinition = "TIMESTAMP")
    private ZonedDateTime actualWakeTime;
    @Embedded
    private SavingDate savingDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_FK")
    private User user;

    @Builder
    public Sleep(ZonedDateTime setSleepTime, ZonedDateTime setWakeTime, User user){
        this.setTime = new SetTime(setSleepTime,setWakeTime);
        this.user = user;
    }

    public void updateActualWakeTime(ZonedDateTime actualWakeTime){
        this.actualWakeTime = actualWakeTime;
    }

    public void updateActualSleepTime(){
        this.savingDate = new SavingDate();
    }

    public void updateSetTime(ZonedDateTime setSleepTime, ZonedDateTime setWakeTime){
        this.setTime = new SetTime(setSleepTime,setWakeTime);
    }
    public Integer assessExperience(ZonedDateTime setSleepTime, ZonedDateTime setWakeTime, ZonedDateTime actualSleepTime, ZonedDateTime actualWakeTime) {
        if (setWakeTime.isAfter(actualSleepTime)) {
            long between = ChronoUnit.HOURS.between(actualSleepTime, setWakeTime);
            long total = ChronoUnit.HOURS.between(setSleepTime, actualWakeTime);
            long experience = (between*5) / total;
            if(experience <0){
                return 0;
            }
            return (int)experience;
        }
        return 0;
    }

}
