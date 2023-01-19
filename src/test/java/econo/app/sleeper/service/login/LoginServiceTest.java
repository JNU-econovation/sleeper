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
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUserId("sleeper");
        signUpRequest.setUserPassword("sleeper1234@");
        signUpRequest.setUserAge(24L);
        signUpRequest.setUserNickName("관리자");

        User user = userService.join(signUpRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserId("sleeper");
        loginRequest.setUserPassword("sleeper1234@");

        //When
        User loginUser = loginService.login(loginRequest);

        //Then
        assertEquals(user.getUserPassword(), loginUser.getUserPassword());
    }

    @Test
    public void login_fail() {
        //Given
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUserId("sleeper");
        signUpRequest.setUserPassword("sleeper1234@");
        signUpRequest.setUserAge(24L);
        signUpRequest.setUserNickName("관리자");

        User user = userService.join(signUpRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserId("sleeper");
        loginRequest.setUserPassword("sleeper1234@");

        //When
        User loginUser = loginService.login(loginRequest);

        //Then
        Assertions.assertThrows(NullPointerException.class,()->loginService.login(loginRequest).getUserId());

    }

}