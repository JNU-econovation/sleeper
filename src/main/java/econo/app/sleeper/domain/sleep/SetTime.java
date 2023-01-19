package econo.app.sleeper.domain.sleep;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.ZonedDateTime;

@Getter
@Embeddable
@NoArgsConstructor
public class SetTime {

    @Column(columnDefinition = "TIMESTAMP")
    private ZonedDateTime setSleepTime;

    @Column(columnDefinition = "TIMESTAMP")
    private ZonedDateTime setWakeTime;

    public SetTime(ZonedDateTime setSleepTime, ZonedDateTime setWakeTime){
        this.setSleepTime = setSleepTime;
        this.setWakeTime = setWakeTime;
    }



}
