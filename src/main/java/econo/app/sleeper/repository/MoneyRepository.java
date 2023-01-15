package econo.app.sleeper.repository;

import econo.app.sleeper.domain.money.Deal;
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

    public void save(Deal moneyDeal){
        em.persist(moneyDeal);
        log.info("save: money={}", moneyDeal);
    }

    public Optional<Deal> findByPk(Long moneyPk){
        Deal moneyDeal = em.find(Deal.class, moneyPk);
        return Optional.of(moneyDeal);
    }

    public Deal findRecentMoneyByUser(Long userPk){
        TypedQuery<Deal> query = em.createQuery("select d from Deal d join d.user u where u.id = :userPk order by d.money.date desc", Deal.class)
                .setParameter("userPk",userPk);
        query.setFirstResult(0);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

}
