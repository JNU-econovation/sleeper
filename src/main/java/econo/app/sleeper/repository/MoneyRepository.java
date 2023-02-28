package econo.app.sleeper.repository;

import econo.app.sleeper.domain.money.Money;
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

    public Optional<Money> findByPk(Long moneyPk){
        Money moneyMoney = em.find(Money.class, moneyPk);
        return Optional.ofNullable(moneyMoney);
    }

    public Optional<Money> findRecentMoneyByUser(Long userPk){
        TypedQuery<Money> query = em.createQuery("select d from Money d join d.user u where u.id = :userPk order by d.money.date desc", Money.class)
                .setParameter("userPk",userPk);
        query.setFirstResult(0);
        query.setMaxResults(1);
        Optional<Money> optionalDeal = Optional.ofNullable(query.getSingleResult());
        return optionalDeal;
    }

}
