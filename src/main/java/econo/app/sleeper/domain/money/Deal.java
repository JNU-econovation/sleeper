package econo.app.sleeper.domain.money;

import econo.app.sleeper.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Getter
@Embeddable
@NoArgsConstructor
public class Deal {

    @Column(name = "MONEY_NOW")
    private Integer holdingMoney;

    @Column(name = "MONEY_CHANGE")
    private Integer changingMoney;

    @Column(name = "MONEY_DATE")
    private ZonedDateTime dateMoney;


    protected Deal(Integer holdingMoney, Integer changingMoney){
        this.holdingMoney = holdingMoney;
        this.changingMoney = changingMoney;
        this.dateMoney = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    protected Deal use(Integer changingMoney) {
        Deal deal = new Deal(holdingMoney + changingMoney , changingMoney);
        return deal;
    }
    
}
