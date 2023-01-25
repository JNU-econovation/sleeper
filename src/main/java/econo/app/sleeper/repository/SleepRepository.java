package econo.app.sleeper.repository;

import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.domain.sleep.Sleep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    public Optional<Sleep> findByPk(Long sleepPk){
        Sleep sleep = em.find(Sleep.class, sleepPk);
        return Optional.ofNullable(sleep);
    }

    // 회원의 수면 기록 중 날짜가 ~ 인 것
    public List<Sleep> findSleepsByUserAndDate(Long userPk, LocalDate localDate){
        return em.createQuery("select s from Sleep s join s.user u where u.id = :userPk and s.savingDate.savingDate = :localDate", Sleep.class)
                .setParameter("userPk",userPk)
                .setParameter("localDate",localDate)
                .getResultList();
    }

    // 해당 회원의 가장 최근의 sleep 기록 찾기 -- 설정수면시간
    public Optional<Sleep> findRecentSleepByUser(Long userPk){
        TypedQuery<Sleep> query = em.createQuery("select s from Sleep s join s.user u where u.id = :userPk order by s.id desc", Sleep.class)
                .setParameter("userPk",userPk);
        query.setFirstResult(0);
        query.setMaxResults(1);
        Optional<Sleep> optionalSleep = Optional.ofNullable(query.getSingleResult());
        return  optionalSleep;
    }

    public List<Sleep> findSleepsBetweenDates(Long userPk, LocalDate startDate, LocalDate endDate){
        return em.createQuery("select s from Sleep s join s.user u where u.id = :userPk and s.savingDate.savingDate between :startDate and :endDate order by s.savingDate.savingDate")
                .setParameter("userPk",userPk)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .getResultList();
    }

}
