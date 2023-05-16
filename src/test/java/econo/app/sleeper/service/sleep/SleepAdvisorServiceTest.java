package econo.app.sleeper.service.sleep;


import econo.app.sleeper.domain.member.Member;
import econo.app.sleeper.service.member.MemberService;
import econo.app.sleeper.web.member.dto.SignUpRequest;
import econo.app.sleeper.web.member.dto.WakeTimeRecommendDto;
import econo.app.sleeper.web.sleep.dto.InitialSleepAdvisorDto;
import econo.app.sleeper.web.sleep.dto.RecommendedTimes;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SleepAdvisorServiceTest {

    @Autowired
    private SleepAdvisorService sleepAdvisorService;
    @Autowired
    private MemberService memberService;

    @Test
    public void create() {
        SignUpRequest signUpRequest = new SignUpRequest("aaa", "aaa12", "아이으", 24L, LocalTime.of(02, 00),LocalTime.of(07, 20),LocalTime.of(5, 20));
        Member member = memberService.join(signUpRequest);
        InitialSleepAdvisorDto initialSleepAdvisorDto = InitialSleepAdvisorDto.of(member, signUpRequest.getGoalSleepTime(), signUpRequest.getGoalWakeTime(), signUpRequest.getMinimumSleepTime());
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