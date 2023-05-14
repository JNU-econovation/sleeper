package econo.app.sleeper.web.login;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class JwtTokenProviderTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void testAccessToken() throws InterruptedException {

        String accessToken = jwtTokenProvider.createAccessToken("bokdungi");
        System.out.println("accessToken = " + accessToken);
        // 만료된 엑세스 토큰 만들기
        //  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib2tkdW5naSIsImlhdCI6MTY4NDA1MTY0NywiZXhwIjoxNjg0MDUxNjQ3fQ.TCst8CFT4L8l6X2B_OydCSi62TBFRfb_ijOiBjZ_Yco

        String refreshToken = jwtTokenProvider.createRefreshToken("bokdungi");
        System.out.println("refreshToken = " + refreshToken);

        // 리프레시 토큰 만들기
        // eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib2tkdW5naSIsImlhdCI6MTY4NDA3NjE5MCwiZXhwIjoxNjg0OTc2MTkwfQ.Kk4SJq6JV7TpRT_KrVS5RIHPi1vqRMbI0X2JDT-x70Y

    }

    @Test
    void test(){

        String accessToken = null;
        Optional<String> accessToken1 = Optional.ofNullable(accessToken);
        System.out.println(accessToken1.isEmpty());

    }
}