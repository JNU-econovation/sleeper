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

    public void save(Money money){
        em.persist(money);
        log.info("save: money={}", money);
    }

    public Optional<Money> findByPk(Long moneyPk){
        Money money = em.find(Money.class, moneyPk);
        return Optional.of(money);
    }

    public Money findRecentMoneyByUser(Long userPk){
        TypedQuery<Money> query = em.createQuery("select m from Money m join m.user u where u.userPk = :userPk order by m.deal.dateMoney desc", Money.class)
                .setParameter("userPk",userPk);
        query.setFirstResult(0);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

}
