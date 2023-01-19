package econo.app.sleeper.service;

import econo.app.sleeper.domain.character.Character;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.repository.CharacterRepository;
import econo.app.sleeper.service.character.CharacterService;
import econo.app.sleeper.service.user.UserService;
import econo.app.sleeper.web.user.SignUpRequest;
import econo.app.sleeper.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void join() {
        //Given
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUserId("bokdungi");
        signUpRequest.setUserPassword("bokdungi");
        signUpRequest.setUserNickName("복덩이");
        signUpRequest.setUserAge(24L);
        signUpRequest.setGoalSleepTime(LocalTime.of(01,30));
        signUpRequest.setGoalWakeTime(LocalTime.of(07,40));

        //When
        User user = userService.join(signUpRequest);


        Character character = Character.createCharacter(user);
        characterRepository.save(character);

        //Then
        Assertions.assertThat(signUpRequest.getUserId()).isEqualTo(user.getUserId());
    }
}