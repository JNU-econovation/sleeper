package econo.app.sleeper.repository;

import econo.app.sleeper.domain.Sleep;
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
        return Optional.of(sleep);
    }

    // 회원의 수면 기록 중 날짜가 ~ 인 것

    public List<Sleep> findSleepsByDate(Long userPk, LocalDate localDate){
        return em.createQuery("select s from Sleep s join s.user u where u.userPk = :userPk and s.savingDate = :localDate", Sleep.class)
                .setParameter("userPk",userPk)
                .setParameter("localDate",localDate)
                .getResultList();
    }

    // 해당 회원의 가장 최근의 sleep 기록 찾기
    public Sleep findRecentSleepByUser(Long userPk){
        TypedQuery<Sleep> query = em.createQuery("select s from Sleep s join s.user u where u.userPk = :userPk order by s.setSleepTime desc", Sleep.class)
                .setParameter("userPk",userPk);
        query.setFirstResult(0);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
    



}
