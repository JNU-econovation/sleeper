package econo.app.sleeper.service.user;

import econo.app.sleeper.domain.user.RoleType;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.domain.user.UserRepository;
import econo.app.sleeper.web.user.dto.SignUpRequest;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void join() {
        SignUpRequest signUpRequest = new SignUpRequest("aaa", "aaa12", "아이으", 24L, LocalTime.of(02, 00),LocalTime.of(07, 20),LocalTime.of(5, 20));
        User user = userService.join(signUpRequest);
        Assertions.assertThat(user.getUserId()).isEqualTo("aaa");
    }

}