package econo.app.sleeper.domain;

import econo.app.sleeper.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(columnDefinition = "TIMESTAMP")
    private ZonedDateTime setSleepTime;

    @Column(columnDefinition = "TIMESTAMP")
    private ZonedDateTime setWakeTime;

    @Column(columnDefinition = "TIMESTAMP")
    private ZonedDateTime actualSleepTime;

    @Column(columnDefinition = "TIMESTAMP")
    private ZonedDateTime actualWakeTime;

    @Column(name = "SLEEP_DATE", columnDefinition = "DATE")
    private LocalDate savingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_FK")
    private User user;


    @Builder
    public Sleep(ZonedDateTime setSleepTime, ZonedDateTime setWakeTime, User user){
        this.setSleepTime = setSleepTime;
        this.setWakeTime = setWakeTime;
        this.user = user;
    }

    public void updateActualWakeTime(ZonedDateTime actualWakeTime){
        this.actualWakeTime = actualWakeTime;
    }

    public void updateActualSleepTime(ZonedDateTime actualSleepTime){
        this.actualSleepTime = actualSleepTime;
    }

    public void updateSetTime(ZonedDateTime setSleepTime, ZonedDateTime setWakeTime){
        this.setSleepTime = setSleepTime;
        this.setWakeTime = setWakeTime;
    }

    public void updateSavingDate(LocalDate savingDate){
        this.savingDate = savingDate;
    }


    public Integer assessExperience(ZonedDateTime setSleepTime, ZonedDateTime setWakeTime, ZonedDateTime actualSleepTime, ZonedDateTime actualWakeTime) {
        if (setWakeTime.isAfter(actualSleepTime)) {
            long between = ChronoUnit.HOURS.between(actualSleepTime, setWakeTime);
            long total = ChronoUnit.HOURS.between(setSleepTime, actualWakeTime);
            long experience = (between*5) / total;
            return (int)experience;
        }
        return 0;
    }

}
