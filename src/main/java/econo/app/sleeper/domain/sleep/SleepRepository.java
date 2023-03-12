package econo.app.sleeper.domain.sleep;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public Optional<Sleep> find(Long sleepPk){
        Sleep sleep = em.find(Sleep.class, sleepPk);
        return Optional.ofNullable(sleep);
    }

    // 회원의 수면 기록 중 날짜가 ~ 인 것
    public List<Sleep> findSleepsByUserAndDate(Long userPk, LocalDate localDate){
        return em.createQuery("select s from Sleep s join s.user u where u.id = :userPk and s.sleepDate = :localDate", Sleep.class)
                .setParameter("userPk",userPk)
                .setParameter("localDate",localDate)
                .getResultList();
    }

    public List<Sleep> findSleepsBetweenDates(Long userPk, LocalDate startDate, LocalDate endDate){
        return em.createQuery("select s from Sleep s join s.user u where u.id = :userPk and s.sleepDate between :startDate and :endDate order by s.sleepDate")
                .setParameter("userPk",userPk)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .getResultList();
    }

}
