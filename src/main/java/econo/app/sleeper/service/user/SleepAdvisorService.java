package econo.app.sleeper.service.user;

import econo.app.sleeper.domain.sleep.SleepAdvisor;
import econo.app.sleeper.exception.RestApiException;
import econo.app.sleeper.exception.error.CommonErrorCode;
import econo.app.sleeper.repository.SleepAdvisorRepository;
import econo.app.sleeper.web.user.RecommendedTimes;
import econo.app.sleeper.web.user.SignUpRequest;
import econo.app.sleeper.web.user.WakeTimeRecommendDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SleepAdvisorService {

    private final SleepAdvisorRepository sleepAdvisorRepository;

    @Transactional
    public void create(SignUpRequest signUpRequest) {
        SleepAdvisor sleepAdvisor = SleepAdvisor.createSleepAdvisor(signUpRequest.getGoalSleepTime(), signUpRequest.getGoalWakeTime(), signUpRequest.getMinimumSleepTime());
        sleepAdvisorRepository.save(sleepAdvisor);
    }

    public RecommendedTimes recommendWakeTimes(WakeTimeRecommendDto wakeTimeRecommendDto){
        SleepAdvisor sleepAdvisor = sleepAdvisorRepository.find(wakeTimeRecommendDto.getUserSleepId())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        List<LocalTime> recommendWakeTimes = sleepAdvisor.recommendWakeTimes(wakeTimeRecommendDto.getExpectedSleepTime());
        RecommendedTimes recommendedTimes = RecommendedTimes.of(recommendWakeTimes);
        return recommendedTimes;
    }
}
