package econo.app.sleeper.repository;

import econo.app.sleeper.domain.User;
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
public class UserRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(User user){
        em.persist(user);
        log.info("save: member={}", user);
    }

    public Optional<User> findOne(Long pk) {
        User user = em.find(User.class,pk);
        Optional<User> optionalUser = Optional.of(user);
        return optionalUser;
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u",User.class).getResultList();
    }

    // 로그인시 사용
    public Optional<User> findById(String userId){
       return em.createQuery("select u from User u where u.userId = :userId ",User.class)
                .setParameter("userId",userId)
               .getResultStream().findFirst();
    }

}
