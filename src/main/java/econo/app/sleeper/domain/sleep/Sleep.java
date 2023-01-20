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
    public Integer assessExperience(){
        if(savingDate.getSavingDateTime().isAfter(setTime.getSetWakeTime())){
            return 0;
        }else if(actualWakeTime.isBefore(setTime.getSetSleepTime())){
            return 0;
        }else if(actualWakeTime.isAfter(setTime.getSetSleepTime()) & actualWakeTime.isBefore(setTime.getSetWakeTime())){
            long between = ChronoUnit.HOURS.between(setTime.getSetSleepTime(), actualWakeTime);
            return (int)between;
        }else if(savingDate.getSavingDateTime().isBefore(setTime.getSetSleepTime()) & actualWakeTime.isAfter(setTime.getSetWakeTime())){
            long between = ChronoUnit.HOURS.between(setTime.getSetSleepTime(), setTime.getSetWakeTime());
            return (int)between;
        } else if (savingDate.getSavingDateTime().isAfter(setTime.getSetSleepTime()) & savingDate.getSavingDateTime().isBefore(setTime.getSetWakeTime())) {
            long between = ChronoUnit.HOURS.between(savingDate.getSavingDateTime(), setTime.getSetWakeTime());
            return (int)between;
        } else if (actualWakeTime.isAfter(setTime.getSetSleepTime()) & savingDate.getSavingDateTime().isBefore(setTime.getSetWakeTime())) {
            long between = ChronoUnit.HOURS.between(savingDate.getSavingDateTime(), actualWakeTime);
            return (int)between;
        }
        return 0;
    }

    public Integer assessScore(){
        if(savingDate.getSavingDateTime().isAfter(setTime.getSetWakeTime())){
            return 0;
        }else if(actualWakeTime.isBefore(setTime.getSetSleepTime())){
            return 0;
        }else if(actualWakeTime.isAfter(setTime.getSetSleepTime()) & actualWakeTime.isBefore(setTime.getSetWakeTime())){
            long total = ChronoUnit.HOURS.between(savingDate.getSavingDateTime(), setTime.getSetWakeTime());
            long between = ChronoUnit.HOURS.between(setTime.getSetSleepTime(), actualWakeTime);
            return (int)(between/total) * 100;
        }else if(savingDate.getSavingDateTime().isBefore(setTime.getSetSleepTime()) & actualWakeTime.isAfter(setTime.getSetWakeTime())){
            long total = ChronoUnit.HOURS.between(savingDate.getSavingDateTime(), actualWakeTime);
            long between = ChronoUnit.HOURS.between(setTime.getSetSleepTime(), setTime.getSetWakeTime());
            System.out.println("between = " + between);
            System.out.println("total = " + total);
            return (int)(between*100/total);
        } else if (savingDate.getSavingDateTime().isAfter(setTime.getSetSleepTime()) & savingDate.getSavingDateTime().isBefore(setTime.getSetWakeTime())) {
            long total = ChronoUnit.HOURS.between(setTime.getSetSleepTime(), actualWakeTime);
            long between = ChronoUnit.HOURS.between(savingDate.getSavingDateTime(), setTime.getSetWakeTime());
            return (int)(between/total) * 100;
        } else if (actualWakeTime.isAfter(setTime.getSetSleepTime()) & savingDate.getSavingDateTime().isBefore(setTime.getSetWakeTime())) {
            long total = ChronoUnit.HOURS.between(setTime.getSetSleepTime(), setTime.getSetWakeTime());
            long between = ChronoUnit.HOURS.between(savingDate.getSavingDateTime(), actualWakeTime);
            return (int)(between/total) * 100;
        }
        return 0;
    }


}
