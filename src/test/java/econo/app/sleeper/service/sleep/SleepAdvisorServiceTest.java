package econo.app.sleeper.service.sleep;

import econo.app.sleeper.domain.sleep.SleepAdvisor;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.domain.user.UserRepository;
import econo.app.sleeper.service.user.UserService;
import econo.app.sleeper.web.sleep.dto.InitialSleepAdvisorDto;
import econo.app.sleeper.web.sleep.dto.RecommendedTimes;
import econo.app.sleeper.web.sleep.dto.SleepAdvisorDto;
import econo.app.sleeper.web.user.dto.SignUpRequest;
import econo.app.sleeper.web.user.dto.WakeTimeRecommendDto;
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
public class SleepAdvisorServiceTest {

    @Autowired
    private SleepAdvisorService sleepAdvisorService;
    @Autowired
    private UserService userService;

    @Test
    public void create() {
        SignUpRequest signUpRequest = new SignUpRequest("aaa", "aaa12", "아이으", 24L, LocalTime.of(02, 00),LocalTime.of(07, 20),LocalTime.of(5, 20));
        User user = userService.join(signUpRequest);
        InitialSleepAdvisorDto initialSleepAdvisorDto = InitialSleepAdvisorDto.of(user, signUpRequest.getGoalSleepTime(), signUpRequest.getGoalWakeTime(), signUpRequest.getMinimumSleepTime());
        sleepAdvisorService.create(initialSleepAdvisorDto);
        Assertions.assertThat(initialSleepAdvisorDto.getMinimumSleepTime()).isEqualTo("05:20");
    }

    @Test
    public void recommendWakeTimes() {
        LocalTime time = LocalTime.of(01, 30);
        WakeTimeRecommendDto wakeTimeRecommendDto = WakeTimeRecommendDto.of(1L, time);
        RecommendedTimes recommendedTimes = sleepAdvisorService.recommendWakeTimes(wakeTimeRecommendDto);
        System.out.println("recommendedTimes = " + recommendedTimes.getRecommendedTimes().get(0));
    }


}