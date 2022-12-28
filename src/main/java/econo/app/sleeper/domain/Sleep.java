package econo.app.sleeper.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_FK")
    private User user;

}
