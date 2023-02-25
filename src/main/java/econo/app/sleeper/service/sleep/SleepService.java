package econo.app.sleeper.service.sleep;

import econo.app.sleeper.domain.sleep.Sleep;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.exception.RestApiException;
import econo.app.sleeper.exception.error.CommonErrorCode;
import econo.app.sleeper.repository.SleepRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.web.sleep.SetTimeRequest;
import econo.app.sleeper.web.sleep.SetTimeResponse;
import econo.app.sleeper.web.sleep.SleepDto;
import econo.app.sleeper.web.sleep.SleepScoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SleepService {

    private final SleepRepository sleepRepository;
    private final UserRepository userRepository;

    @Transactional
    public Sleep saveSetTime(Long userPk){
        User user = userRepository.find(userPk).get();
        List<ZonedDateTime> zonedDateTimes = user.getGoalTime().toLocalDateTime();
        Sleep sleep = Sleep.createSetSleep(zonedDateTimes.get(0), zonedDateTimes.get(1), user);
        sleepRepository.save(sleep);
        return sleep;
    }

    public SetTimeResponse readSetTime(Long sleepPk){
        Sleep sleep = sleepRepository.findByPk(sleepPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        SetTimeResponse setTimeResponse = SetTimeResponse.of(sleep.getAlarm().getSetSleepTime(), sleep.getAlarm().getSetWakeTime());
        return setTimeResponse;
    }

    @Transactional
    public void updateActualWakeTime(SleepDto sleepDto){
        Sleep sleep = sleepRepository.findByPk(sleepDto.getSleepPk())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        sleep.updateActualWakeTime(sleepDto.getActualWakeTime());
    }

}
