package econo.app.sleeper.repository;

import econo.app.sleeper.domain.Diary;
import econo.app.sleeper.domain.Money;
import econo.app.sleeper.domain.Sleep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

}
