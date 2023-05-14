package econo.app.sleeper.domain.Auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface AuthRepository extends JpaRepository<Auth,Long> {


    @Query("select a from Auth a where a.accessToken = :accessToken and a.id = (select max(b.id) from Auth b where b.accessToken = :accessToken)")
    Auth findByAccessTokenAndId(String accessToken);

}
