package econo.app.sleeper.service.login;

import econo.app.sleeper.domain.User;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.service.user.UserService;
import econo.app.sleeper.web.login.LoginRequestForm;
import econo.app.sleeper.web.user.SignUpRequestForm;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
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
        SignUpRequestForm signUpRequestForm = SignUpRequestForm.builder()
                .userId("sleeper")
                .userPassword("sleeper1234@@")
                .userAge(24L)
                .userNickName("관리자")
                .build();

        User user = userService.join(signUpRequestForm);

        LoginRequestForm loginRequestForm = new LoginRequestForm("sleeper","sleeper1234@@");

        //When
        User loginUser = loginService.login(loginRequestForm);

        //Then
        assertEquals(user.getUserPassword(), loginUser.getUserPassword());
    }

    @Test
    public void login_fail() {
        //Given
        SignUpRequestForm signUpRequestForm = SignUpRequestForm.builder()
                .userId("sleeper")
                .userPassword("sleeper1234@@")
                .userAge(24L)
                .userNickName("관리자")
                .build();

        User user = userService.join(signUpRequestForm);

        LoginRequestForm loginRequestForm = new LoginRequestForm("wakeup","wakeup112");

        //When

        //Then
        Assertions.assertThrows(NullPointerException.class,()->loginService.login(loginRequestForm).getUserId());

    }

}