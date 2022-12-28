package econo.app.sleeper.repository;

import econo.app.sleeper.domain.Diary;
import econo.app.sleeper.domain.Sleep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SleepRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Sleep sleep){
        em.persist(sleep);
        log.info("save: sleep={}", sleep);
    }


}
