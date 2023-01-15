package econo.app.sleeper.domain.sleep;


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
    private Long id;
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
    @Builder
    public Sleep(Long id, SetTime setTime, ZonedDateTime actualWakeTime, SavingDate savingDate) {
        this.id = id;
        this.setTime = setTime;
        this.actualWakeTime = actualWakeTime;
        this.savingDate = savingDate;
    }

    public void mappingUser(User user){
        this.user = user;
        user.mappingSleep(this);
    }

    public static Sleep createSetSleep(ZonedDateTime setSleepTime, ZonedDateTime setWakeTime, User user){
        Sleep sleep = new Sleep();
        sleep.setTime = new SetTime(setSleepTime,setWakeTime);
        sleep.mappingUser(user);
        return sleep;
    }

    public void updateActualWakeTime(ZonedDateTime actualWakeTime){
        this.actualWakeTime = actualWakeTime;
    }

    public void updateActualSleepTime(){
        SavingDate savingDate = new SavingDate();
        this.savingDate = savingDate;
    }

    public void updateSetTime(ZonedDateTime setSleepTime, ZonedDateTime setWakeTime){
        SetTime setTime = new SetTime(setSleepTime,setWakeTime);
        this.setTime = setTime;
    }
    public Integer assessExperience() {
        ZonedDateTime setWakeTime = setTime.getSetWakeTime();
        ZonedDateTime setSleepTime = setTime.getSetSleepTime();
        ZonedDateTime actualSleepTime = savingDate.getSavingDateTime();
        if (setWakeTime.isAfter(actualWakeTime)) {
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
