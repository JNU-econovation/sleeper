package econo.app.sleeper.repository;


import econo.app.sleeper.domain.diary.Diary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
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


    public Optional<Diary> findByPk(Long diaryPk){
        Diary diary = em.find(Diary.class, diaryPk);
        return Optional.ofNullable(diary);
    }

    // 회원의 감사일기들 찾기
    public List<Diary> findAllByPk(Long userPk) {
    return em.createQuery("select d from Diary d join d.user u where u.id = :userPk",Diary.class)
            .setParameter("userPk",userPk)
            .getResultList();
    }
    
    //회원의 감사일기들 중 가장 최근의 감사일기 찾기
    public Diary findRecentDiaryByUser(Long userPk){
        TypedQuery<Diary> query = em.createQuery("select d from Diary d join d.user u where u.id = :userPk order by d.savingDate.savingDate desc", Diary.class)
                .setParameter("userPk",userPk);
        query.setFirstResult(0);
        query.setMaxResults(1);
        return query.getSingleResult();
    }


    // 회원의 감사일기들 중 날짜가 ~인 것들 찾기
    public List<Diary> findDiaryByDate(Long userPk, LocalDate localDate){
    return em.createQuery("select d from Diary d join d.user u where u.id = :userPk and d.savingDate.savingDate = :localDate",Diary.class)
            .setParameter("userPk",userPk)
            .setParameter("localDate",localDate)
            .getResultList();
    }
    
    // 회원의 감사일기들 중 날짜가 x년 x월 인 것 찾기
    public List<Diary> findDiaryBetweenDates(Long userPk, LocalDate startDate, LocalDate endDate){
        return em.createQuery("select d from Diary d join d.user u where u.id = :userPk and d.savingDate.savingDate between :startDate and :endDate order by d.savingDate.savingDate")
                .setParameter("userPk",userPk)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .getResultList();
    }

}
