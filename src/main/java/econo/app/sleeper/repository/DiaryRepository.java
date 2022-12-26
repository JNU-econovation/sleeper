package econo.app.sleeper.repository;


import econo.app.sleeper.domain.Diary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DiaryRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Diary diary){
        em.persist(diary);
        log.info("save: diary={}", diary);
    }

    public void delete(Diary diary){
        em.remove(diary);
        log.info("delete: diary={}", diary);
    }

    // 감사일기들 찾기
    public Optional<Diary> findByPk(Long diaryPk){
        Diary diary = em.find(Diary.class, diaryPk);
        return Optional.of(diary);
    }

    // 회원의 감사일기들 찾기
    public List<Diary> findAllByPk(Long userPk) {
    return em.createQuery("select d from Diary d join d.user u where u.userPk = :userPk",Diary.class)
            .setParameter("userPk",userPk)
            .getResultList();
    }

    // 회원의 감사일기들 중 날짜가 ~인 것들 찾기
    public List<Diary> findByDate(Long userPk, LocalDate localDate){
    return em.createQuery("select d from Diary d join d.user u where u.userPk = :userPk and d.localDate = :localDate",Diary.class)
            .setParameter("userPk",userPk)
            .setParameter("localDate",localDate)
            .getResultList();
    }

}
