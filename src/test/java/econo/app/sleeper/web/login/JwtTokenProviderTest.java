package econo.app.sleeper.web.login;

import io.jsonwebtoken.Claims;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class JwtTokenProviderTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void testAccessToken() throws InterruptedException {

        String accessToken1 = jwtTokenProvider.createAccessToken("sleeper");
        String signature1 = jwtTokenProvider.getSignatureAccessToken(accessToken1);

        Thread.sleep(30000);

        String accessToken2 = jwtTokenProvider.createAccessToken("sleeper");
        String signature2 = jwtTokenProvider.getSignatureAccessToken(accessToken2);

        Assertions.assertThat(signature1).isNotEqualTo(signature2);

    }
}