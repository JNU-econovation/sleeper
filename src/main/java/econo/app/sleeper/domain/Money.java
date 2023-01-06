package econo.app.sleeper.domain;

import econo.app.sleeper.util.SpeechBubbleKind;
import io.swagger.models.auth.In;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @OneToOne(mappedBy = "money")
    private User user;

    @Column(name = "MONEY_NOW")
    private Integer holdingMoney;

    @Column(name = "MONEY_CHANGE")
    private Integer changingMoney;

    @Column(name = "MONEY_DATE")
    private ZonedDateTime dateMoney;


    @Builder
    public Money(User user, Integer holdingMoney, Integer changingMoney, ZonedDateTime dateMoney){
        this.user = user;
        this.holdingMoney = holdingMoney;
        this.changingMoney = changingMoney;
        this.dateMoney = dateMoney;
    }


    public static Money initMoney(User user){
        Money money = Money.builder()
                .user(user)
                .dateMoney(ZonedDateTime.now(ZoneId.of("Asia/Seoul")))
                .holdingMoney(0)
                .changingMoney(0)
                .build();
        user.associate(money);
        return money;
    }

    public Money use(Integer changingMoney, Integer holdingMoney ,User user) {
        return Money.builder()
                .changingMoney(changingMoney)
                .holdingMoney(holdingMoney + changingMoney)
                .user(user)
                .dateMoney(ZonedDateTime.now(ZoneId.of("Asia/Seoul")))
                .build();
    }



}
