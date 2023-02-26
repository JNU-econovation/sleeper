package econo.app.sleeper.service.sleep;

import econo.app.sleeper.domain.sleep.SleepAdvisor;
import econo.app.sleeper.exception.RestApiException;
import econo.app.sleeper.exception.error.CommonErrorCode;
import econo.app.sleeper.repository.SleepAdvisorRepository;
import econo.app.sleeper.web.sleep.RecommendedTimes;
import econo.app.sleeper.web.sleep.SleepAdvisorDto;
import econo.app.sleeper.web.sleep.SleepAdvisorResponse;
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
        SleepAdvisor sleepAdvisor = sleepAdvisorRepository.find(wakeTimeRecommendDto.getSleepAdvisorPk())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        List<LocalTime> recommendWakeTimes = sleepAdvisor.recommendWakeTimes(wakeTimeRecommendDto.getExpectedSleepTime());
        RecommendedTimes recommendedTimes = RecommendedTimes.of(recommendWakeTimes);
        return recommendedTimes;
    }

    @Transactional
    public SleepAdvisorResponse updateSleepAdvisor(SleepAdvisorDto sleepAdvisorDto){
        SleepAdvisor sleepAdvisor = sleepAdvisorRepository.find(sleepAdvisorDto.getSleepAdvisorPk())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        sleepAdvisor.updateSleepAdvisor(sleepAdvisor.getGoalSleepTime(),sleepAdvisor.getGoalWakeTime(),sleepAdvisor.getMinimumSleepTime());
        SleepAdvisorResponse sleepAdvisorResponse = SleepAdvisorResponse.of(sleepAdvisor.getGoalSleepTime(), sleepAdvisor.getGoalWakeTime(), sleepAdvisor.getMinimumSleepTime());
        return sleepAdvisorResponse;
    }
}
