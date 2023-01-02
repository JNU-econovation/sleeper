package econo.app.sleeper.service.sleep;

import econo.app.sleeper.domain.Character;
import econo.app.sleeper.repository.CharacterRepository;
import econo.app.sleeper.repository.SleepRepository;
import econo.app.sleeper.util.SpeechBubbleKind;
import econo.app.sleeper.web.sleep.SetTimeDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class SleepServiceTest {

    @Autowired
    SleepRepository sleepRepository;

    @Autowired
    SleepService sleepService;

    @Autowired
    CharacterRepository characterRepository;

    @Test
    public void saveSetTime() {
        LocalDateTime setSleepTime = LocalDateTime.of(2023, 01, 03, 1, 00);
        LocalDateTime setWakeTime = LocalDateTime.of(2023, 01, 03, 8, 00);
        sleepService.saveSetTime(SetTimeDto.of(setSleepTime,setWakeTime,"sleeper"));
    }

    @Test
    public void checkOverSetSleep(){
        LocalDateTime setSleepTime = LocalDateTime.of(2023, 01, 03, 1, 00);
        LocalDateTime setWakeTime = LocalDateTime.of(2023, 01, 03, 8, 00);
        sleepService.saveSetTime(SetTimeDto.of(setSleepTime,setWakeTime,"sleeper"));

        sleepService.checkOverSetSleep("sleeper");
        Character character = characterRepository.findById("sleeper").get();

        Assertions.assertThat(character.getSpeechBubble()).isEqualTo(SpeechBubbleKind.SLEEP.message());

    }

}