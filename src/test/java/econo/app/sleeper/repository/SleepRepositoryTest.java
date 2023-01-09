package econo.app.sleeper.repository;

import econo.app.sleeper.domain.Sleep.Sleep;
import econo.app.sleeper.service.sleep.SleepService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class SleepRepositoryTest {

    @Autowired
    SleepRepository sleepRepository;

    @Autowired
    SleepService sleepService;
    
    @Test
    public void findSleepsByDate(){

        LocalDate localDate = LocalDate.of(2023, 01, 01);
        Long userPk = 1L;

        List<Sleep> sleepsByDate = sleepRepository.findSleepsByDate(userPk, localDate);

        for (Sleep s: sleepsByDate) {
            System.out.println("s.getActualSleepTime() = " + s.getSavingDate().getSavingDateTime());
        }
    }

    @Test
    public void findRecentSleepByUser(){
        sleepService.saveSetTime("sleeper");
        Sleep recentSleepByUser = sleepRepository.findRecentSleepByUser(1L);
        recentSleepByUser.getSetTime().getSetSleepTime().toLocalDateTime().toString();
        System.out.println("recentSleepByUser = " + recentSleepByUser.getSetTime().getSetSleepTime());
        System.out.println("recentSleepByUser = " + recentSleepByUser.getSetTime().getSetWakeTime());
    }

    

}