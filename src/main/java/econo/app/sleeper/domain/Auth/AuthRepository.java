package econo.app.sleeper.domain.Auth;
import econo.app.sleeper.domain.money.Money;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AuthRepository {

    @PersistenceContext
    private final EntityManager em;

    public Long save(Auth auth) {
        em.persist(auth);
        log.info("save: auth={}", auth);
        return auth.getId();
    }

    public String findRecentRefreshToken(String accessToken){
        Query query = em.createQuery("select a.refreshToken from Auth a where a.accessToken = :accessToken and a.id = max(a.id)")
                .setParameter("accessToken", accessToken);
        Auth auth = (Auth) query.getSingleResult();
        return auth.getRefreshToken();
    }

}
