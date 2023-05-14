package econo.app.sleeper.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
        log.info("save: member={}", member);
    }

    public Optional<Member> find(Long pk) {
        Member member = em.find(Member.class, pk);
        Optional<Member> optionalUser = Optional.ofNullable(member);
        return optionalUser;
    }

    public List<Member> findAll() {
        return em.createQuery("select u from Member u", Member.class).getResultList();
    }

    public Optional<Member> findById(String userId) {
        return em.createQuery("select u from Member u where u.memberId = :userId ", Member.class)
                .setParameter("userId", userId)
                .getResultStream().findFirst();
    }
}
