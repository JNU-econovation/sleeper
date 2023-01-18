package econo.app.sleeper.domain.money;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Getter
@Embeddable
@NoArgsConstructor
public class Money {

    @Column(name = "MONEY_NOW")
    private Integer nowMoney;

    @Column(name = "MONEY_CHANGE")
    private Integer usingMoney;

    @Column(name = "MONEY_DATE")
    private ZonedDateTime date;


    protected Money(Integer nowMoney, Integer usingMoney){
        this.nowMoney = nowMoney;
        this.usingMoney = usingMoney;
        this.date = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    protected Money use(Integer changingMoney) {
        Money money = new Money(nowMoney + changingMoney , changingMoney);
        return money;
    }
    
}
