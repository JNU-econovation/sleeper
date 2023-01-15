package econo.app.sleeper.domain.money;

import econo.app.sleeper.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "DEAL")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Money money;

    @OneToOne(mappedBy = "deal")
    private User user;

    @Builder
    public Deal(User user, Integer holdingMoney, Integer changingMoney){
        this.money = new Money(holdingMoney,changingMoney);
        this.user = user;
    }

    public Deal(User user, Money money){
        this.user = user;
        this.money = money;
    }

    public void mappingUser(User user){
        this.user = user;
        user.mappingMoney(this);
    }

    public static Deal createMoney(User user){
        Money money = new Money(0,0);
        Deal moneyDeal = new Deal(user, money);
        moneyDeal.mappingUser(user);
        return moneyDeal;
    }


    public Deal plusMoney(Integer changingMoney) {
        // changingMoney < 0 일때 예외처리하기
        Money money = this.money.use(changingMoney);
        Deal moneyDeal = new Deal(user, money);
        return moneyDeal;
    }

}
