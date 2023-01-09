package econo.app.sleeper.service.login;

import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.service.user.UserService;
import econo.app.sleeper.web.login.LoginRequest;
import econo.app.sleeper.web.user.SignUpRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LoginServiceTest {

    @Autowired
    LoginService loginService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    UserRepository userRepository;


    @Test
    public void login_success() {
        //Given
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .userId("sleeper")
                .userPassword("sleeper1234@@")
                .userAge(24L)
                .userNickName("관리자")
                .build();

        User user = userService.join(signUpRequest);

        LoginRequest loginRequest = new LoginRequest("sleeper","sleeper1234@@");

        //When
        User loginUser = loginService.login(loginRequest);

        //Then
        assertEquals(user.getUserPassword(), loginUser.getUserPassword());
    }

    @Test
    public void login_fail() {
        //Given
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .userId("sleeper")
                .userPassword("sleeper1234@@")
                .userAge(24L)
                .userNickName("관리자")
                .build();

        User user = userService.join(signUpRequest);

        LoginRequest loginRequest = new LoginRequest("wakeup","wakeup112");

        //When

        //Then
        Assertions.assertThrows(NullPointerException.class,()->loginService.login(loginRequest).getUserId());

    }

}