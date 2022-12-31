package econo.app.sleeper.repository;

import econo.app.sleeper.domain.Sleep;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class SleepRepositoryTest {

    @Autowired
    SleepRepository sleepRepository;
    
    @Test
    public void findSleepsByDate(){
        LocalDate localDate = LocalDate.of(2022, 12, 25);
        Long userPk = 1L;

        List<Sleep> sleepsByDate = sleepRepository.findSleepsByDate(userPk, localDate);

        for (Sleep s: sleepsByDate) {
            System.out.println("s.getActualSleepTime() = " + s.getActualSleepTime());
        }
    }
    
    

}