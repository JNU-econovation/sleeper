package econo.app.sleeper.service;

import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.service.user.UserService;
import econo.app.sleeper.web.user.SignUpRequest;
import econo.app.sleeper.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    public void join() {
        //Given
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUserId("sleeper");
        signUpRequest.setUserPassword("sleeper1234@");
        signUpRequest.setUserAge(24L);
        signUpRequest.setUserNickName("관리자");

        //When
        User user = userService.join(signUpRequest);

        //Then
        System.out.println("user = " + user.getUserNickName());
        assertEquals("관리자", user.getUserNickName());
    }
}