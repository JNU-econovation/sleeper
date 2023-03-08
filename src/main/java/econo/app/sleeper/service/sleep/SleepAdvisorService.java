package econo.app.sleeper.service.sleep;

import econo.app.sleeper.domain.sleep.SleepAdvisor;
import econo.app.sleeper.exception.RestApiException;
import econo.app.sleeper.exception.error.CommonErrorCode;
import econo.app.sleeper.domain.sleep.SleepAdvisorRepository;
import econo.app.sleeper.web.sleep.dto.InitialSleepAdvisorDto;
import econo.app.sleeper.web.sleep.dto.RecommendedTimes;
import econo.app.sleeper.web.sleep.dto.SleepAdvisorDto;
import econo.app.sleeper.web.sleep.dto.SleepAdvisorResponse;
import econo.app.sleeper.web.user.dto.WakeTimeRecommendDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SleepAdvisorService {

    private final SleepAdvisorRepository sleepAdvisorRepository;

    @Autowired
    public SleepAdvisorService (SleepAdvisorRepository sleepAdvisorRepository){
        this.sleepAdvisorRepository = sleepAdvisorRepository;
    }

    @Transactional
    public void create(InitialSleepAdvisorDto initialSleepAdvisorDto) {
        SleepAdvisor sleepAdvisor = SleepAdvisor.createSleepAdvisor(initialSleepAdvisorDto.getGoalSleepTime(), initialSleepAdvisorDto.getGoalWakeTime(), initialSleepAdvisorDto.getMinimumSleepTime(), initialSleepAdvisorDto.getUser());
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
