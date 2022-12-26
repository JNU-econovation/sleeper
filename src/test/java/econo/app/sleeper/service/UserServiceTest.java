package econo.app.sleeper.service;

import econo.app.sleeper.domain.User;
import econo.app.sleeper.service.user.UserService;
import econo.app.sleeper.web.user.SignUpRequestForm;
import econo.app.sleeper.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;


    @Test
    public void join() {
        //Given
        SignUpRequestForm signUpRequestForm = SignUpRequestForm.builder()
                .userId("sleeper")
                .userPassword("sleeper1234@@")
                .userAge(24L)
                .userNickName("관리자")
                .build();

        //When
        User user = userService.join(signUpRequestForm);

        //Then
        System.out.println("user = " + user.getUserNickName());
        assertEquals("관리자", user.getUserNickName());
    }
}