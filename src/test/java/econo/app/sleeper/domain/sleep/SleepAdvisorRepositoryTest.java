package econo.app.sleeper.domain.sleep;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SleepAdvisorRepositoryTest {

    @Autowired
    private SleepAdvisorRepository sleepAdvisorRepository;


    @Test
    public void find() {
        SleepAdvisor sleepAdvisor = sleepAdvisorRepository.find(1L).get();
        LocalTime goalSleepTime = sleepAdvisor.getGoalSleepTime();
        Assertions.assertThat(goalSleepTime).isEqualTo("01:40");
    }
}