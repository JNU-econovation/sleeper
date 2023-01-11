package econo.app.sleeper.domain.money;

import econo.app.sleeper.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MONEY")
public class Money {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moneyPk;
    @Embedded
    private Deal deal;

    @OneToOne(mappedBy = "money")
    private User user;

    @Builder
    public Money(User user,Integer holdingMoney, Integer changingMoney){
        this.deal = new Deal(holdingMoney,changingMoney);
        this.user = user;
    }

    public Money(User user, Deal deal){
        this.user = user;
        this.deal = deal;
    }

    public void associate(User user){
        this.user = user;
    }

    public Money use(Integer changingMoney) {
        Deal deal = this.deal.use(changingMoney);
        Money money = new Money(user,deal);
        return money;
    }

    public static Money init(User user){
        Deal deal = new Deal(0,0);
        Money money = new Money(user, deal);
        user.associate(money);
        return money;
    }

}
