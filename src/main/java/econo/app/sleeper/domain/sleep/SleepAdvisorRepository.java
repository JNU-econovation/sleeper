package econo.app.sleeper.domain.sleep;

import econo.app.sleeper.domain.sleep.SleepAdvisor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SleepAdvisorRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(SleepAdvisor sleepAdvisor) {
        em.persist(sleepAdvisor);
        log.info("save: userSleepInfo={}", sleepAdvisor);
    }

    public Optional<SleepAdvisor> find(Long pk) {
        SleepAdvisor sleepAdvisor = em.find(SleepAdvisor.class, pk);
        Optional<SleepAdvisor> optionalUserSleepInfo = Optional.ofNullable(sleepAdvisor);
        return optionalUserSleepInfo;
    }
}
