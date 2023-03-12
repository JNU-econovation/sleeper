package econo.app.sleeper.domain.sleep;

import econo.app.sleeper.domain.user.RoleType;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.domain.user.UserRepository;
import econo.app.sleeper.domain.user.UserRepositoryTest;
import org.assertj.core.api.Assertions;
import org.junit.Before;
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
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SleepRepositoryTest {

    @Autowired
    private SleepRepository sleepRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init(){
        User user = User.createUser("test12", "test12", "test12", 25L, RoleType.USER);
        userRepository.save(user);
        ZonedDateTime setSleepTime = ZonedDateTime.of(LocalDateTime.of(2023, 03, 03, 01, 40), ZoneId.of("Asia/Seoul"));
        ZonedDateTime setWakeTime = ZonedDateTime.of(LocalDateTime.of(2023, 03, 03, 9, 00), ZoneId.of("Asia/Seoul"));
        ZonedDateTime actualSleepTime = ZonedDateTime.of(LocalDateTime.of(2023, 03, 03, 02, 10), ZoneId.of("Asia/Seoul"));
        ZonedDateTime actualWakeTime = ZonedDateTime.of(LocalDateTime.of(2023, 03, 03, 8, 40), ZoneId.of("Asia/Seoul"));
        LocalDate sleepDae = LocalDate.of(2023, 03, 02);
        Sleep sleep = Sleep.createSleep(setSleepTime, setWakeTime, actualSleepTime, sleepDae, user);
        sleepRepository.save(sleep);
        sleep.updateActualWakeTime(actualWakeTime);
    }

    @Test
    public void find() {
        Sleep sleep = sleepRepository.find(1L).get();
        Assertions.assertThat(sleep.getActualSleepTime()).isEqualTo("2023-03-05T01:36:00+09:00");
    }

    @Test
    public void findSleepsByUserAndDate() {
        List<Sleep> sleepsByUserAndDate = sleepRepository.findSleepsByUserAndDate(2L, LocalDate.of(2023, 03, 02));
        Assertions.assertThat(sleepsByUserAndDate.size()).isEqualTo(1);
    }

    @Test
    public void findSleepsBetweenDates() {
        List<Sleep> sleepsBetweenDates = sleepRepository.findSleepsBetweenDates(1L, LocalDate.of(2023, 03, 01), LocalDate.of(2023, 03, 31));
        Assertions.assertThat(sleepsBetweenDates.size()).isEqualTo(1);
    }

    @Test
    public void findSleepsBetweenDates_2월_마지막_날_일때() {
        ZonedDateTime setSleepTime2 = ZonedDateTime.of(LocalDateTime.of(2023, 03, 01, 01, 40), ZoneId.of("Asia/Seoul"));
        ZonedDateTime setWakeTime2 = ZonedDateTime.of(LocalDateTime.of(2023, 03, 01, 9, 00), ZoneId.of("Asia/Seoul"));
        ZonedDateTime actualSleepTime2 = ZonedDateTime.of(LocalDateTime.of(2023, 03, 01, 02, 10), ZoneId.of("Asia/Seoul"));
        ZonedDateTime actualWakeTime2 = ZonedDateTime.of(LocalDateTime.of(2023, 03, 01, 8, 40), ZoneId.of("Asia/Seoul"));
        LocalDate sleepDae2 = LocalDate.of(2023, 02, 28);
        User user = userRepository.find(2L).get();
        Sleep sleep2 = Sleep.createSleep(setSleepTime2, setWakeTime2, actualSleepTime2, sleepDae2, user);
        sleepRepository.save(sleep2);
        sleep2.updateActualWakeTime(actualWakeTime2);

        List<Sleep> sleepsBetweenDates = sleepRepository.findSleepsBetweenDates(2L, LocalDate.of(2023, 03, 01), LocalDate.of(2023, 03, 31));
        Assertions.assertThat(sleepsBetweenDates.size()).isEqualTo(1);

        List<Sleep> sleepsBetweenDates2 = sleepRepository.findSleepsBetweenDates(2L, LocalDate.of(2023, 02, 01), LocalDate.of(2023, 02, 28));
        Assertions.assertThat(sleepsBetweenDates2.size()).isEqualTo(1);
    }

}
