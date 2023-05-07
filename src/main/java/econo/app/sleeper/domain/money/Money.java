package econo.app.sleeper.domain.money;

import econo.app.sleeper.domain.member.Member;
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
    private Long id;

    @Column(name = "MONEY_HOLDING_CASH")
    private Integer holdingCash;

    @Column(name = "MONEY_DATE")
    private ZonedDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_FK")
    private Member member;

    @Builder
    public Money(Integer holdingCash, ZonedDateTime date){
        this.holdingCash = holdingCash;
        this.date = date;
    }
    public void mappingUser(Member member) {
        this.member = member;
    }

    public static Money createMoney(Member member){
        Money money = new Money();
        money.holdingCash = 0;
        money.date = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        money.mappingUser(member);
        return money;
    }

    public Money plusCash(Integer cash) throws IllegalStateException {
        if(cash < 0){
            throw new IllegalStateException("음수가 될 수 없습니다");
        }
        Money money = new Money(this.holdingCash + cash, ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
        return money;
    }

    public Money useCash(Integer cash) {
        if(cash < 0){
            throw new IllegalStateException("음수가 될 수 없습니다");
        }
        if(this.holdingCash - cash < 0){
            throw new IllegalStateException("캐쉬가 부족합니다!");
        }
        Money money = new Money(this.holdingCash - cash, ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
        return money;
    }


}
