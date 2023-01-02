package econo.app.sleeper.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

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

    public void updateActualTime(ZonedDateTime actualSleepTime, ZonedDateTime actualWakeTime){
        this.actualSleepTime = actualSleepTime;
        this.actualWakeTime = actualWakeTime;
    }

    public void updateSavingDate(LocalDate savingDate){
        this.savingDate = savingDate;
    }

}
