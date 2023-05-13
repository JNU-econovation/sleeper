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

@RunWith(SpringRunner.class)
@SpringBootTest
class JwtTokenProviderTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void testAccessToken() throws InterruptedException {

        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey("591a894d557edd952aa186c3e4d69de12caa543e059e88cc6ff26b082df94a63c998293a677a1d8475f2c1215e2a32f2a6bdb81d1d8908d43294bffa1c3d9357").build();
        jwtParser.parseClaimsJws("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib2tkdW5naSIsImlhdCI6MTY4NDAwNDE2NCwiZXhwIjoxNjg0MDA0NzY0fQ.t6EYa3b7tnNWIyP_BRtaSN2iGvXhc3Vj2SUd0GQimiE");

    }
}