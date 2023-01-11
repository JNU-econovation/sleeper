package econo.app.sleeper.service.sleep;

import econo.app.sleeper.domain.character.Character;
import econo.app.sleeper.domain.sleep.Sleep;
import econo.app.sleeper.repository.CharacterRepository;
import econo.app.sleeper.repository.SleepRepository;
import econo.app.sleeper.domain.character.SpeechBubble;
import econo.app.sleeper.web.sleep.SetTimeDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;

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
        Sleep sleep = sleepService.saveSetTime("sleeper");
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Seoul"));
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(23, 30));
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("Asia/Seoul"));
        Assertions.assertThat(sleep.getSetTime().getSetSleepTime()).isEqualTo(zonedDateTime);
    }


}