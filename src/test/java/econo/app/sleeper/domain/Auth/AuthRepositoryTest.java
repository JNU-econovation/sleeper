package econo.app.sleeper.domain.Auth;

import econo.app.sleeper.web.login.JwtTokenProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
class AuthRepositoryTest {

    @Autowired
    private AuthRepository authRepository;

    @Test
    void save() {
        Auth auth = Auth.createAuth("accessToken", "refreshToken");
        authRepository.save(auth);
        int size = authRepository.findAll().size();
        Assertions.assertThat(1).isEqualTo(size);
    }

    @DisplayName("가장 최근의 리프레시 토큰이 찾아 지는 지 확인 - 엑세스 토큰 : 리프레시 토큰 -> 1:N 관계")
    @Test
    void findRecentRefreshToken() throws InterruptedException {
        Auth auth = Auth.createAuth("accessToken","refreshToken");
        Auth auth1 = Auth.createAuth("accessToken", "refreshToken1");
        authRepository.save(auth);
        authRepository.save(auth1);
        String newRefreshToken = authRepository.findByAccessTokenAndId("accessToken").getRefreshToken();
        Assertions.assertThat("refreshToken1").isEqualTo(newRefreshToken);
    }
}