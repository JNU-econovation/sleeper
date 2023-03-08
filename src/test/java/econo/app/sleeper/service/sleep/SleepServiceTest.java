package econo.app.sleeper.service.sleep;

import econo.app.sleeper.domain.sleep.Sleep;
import econo.app.sleeper.domain.sleep.SleepRepository;
import econo.app.sleeper.web.sleep.dto.SleepRequest;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SleepServiceTest {

    @Autowired
    private SleepService sleepService;

    @Autowired
    private SleepRepository sleepRepository;

    @Test
    public void saveSleep() {
        ZonedDateTime setSleepTime = ZonedDateTime.of(LocalDateTime.of(2023, 03, 03, 01, 40), ZoneId.of("Asia/Seoul"));
        ZonedDateTime setWakeTime = ZonedDateTime.of(LocalDateTime.of(2023, 03, 03, 9, 00), ZoneId.of("Asia/Seoul"));
        ZonedDateTime actualSleepTime = ZonedDateTime.of(LocalDateTime.of(2023, 03, 03, 02, 10), ZoneId.of("Asia/Seoul"));
        SleepRequest sleepRequest = new SleepRequest(setSleepTime,setWakeTime,actualSleepTime,1L,1L);
        Long sleepPk = sleepService.saveSleep(sleepRequest);
        Sleep sleep = sleepRepository.find(sleepPk).get();
        String date = LocalDate.of(2023, 03, 02).toString();
        Assertions.assertThat(sleep.getSleepDate()).isEqualTo(date);
    }
}