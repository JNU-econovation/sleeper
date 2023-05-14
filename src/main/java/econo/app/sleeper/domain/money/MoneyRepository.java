package econo.app.sleeper.domain.money;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MoneyRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Money moneyMoney){
        em.persist(moneyMoney);
        log.info("save: money={}", moneyMoney);
    }

    public Optional<Money> find(Long moneyPk){
        Money moneyMoney = em.find(Money.class, moneyPk);
        return Optional.ofNullable(moneyMoney);
    }

    public Optional<Money> findRecentMoneyByUser(Long userPk){
        TypedQuery<Money> query = em.createQuery("select mo from Money mo join mo.member m where m.id = :userPk order by mo.date desc", Money.class)
                .setParameter("userPk",userPk);
        query.setFirstResult(0);
        query.setMaxResults(1);
        Optional<Money> optionalDeal = Optional.ofNullable(query.getSingleResult());
        return optionalDeal;
    }

}
