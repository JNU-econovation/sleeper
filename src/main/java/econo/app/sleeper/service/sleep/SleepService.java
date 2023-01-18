package econo.app.sleeper.service.sleep;

import econo.app.sleeper.domain.sleep.Sleep;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.exception.RestApiException;
import econo.app.sleeper.exception.error.CommonErrorCode;
import econo.app.sleeper.repository.SleepRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.util.DateTypeConverter;
import econo.app.sleeper.web.calendar.CalendarDto;
import econo.app.sleeper.web.sleep.SetTimeDto;
import econo.app.sleeper.web.sleep.SetTimeResponse;
import econo.app.sleeper.web.sleep.SleepDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        SetTimeResponse setTimeResponse = SetTimeResponse.of(sleep.getSetTime().getSetSleepTime(), sleep.getSetTime().getSetWakeTime());
        return setTimeResponse;
    }

    @Transactional
    public void updateSetTime(Long sleepPk,SetTimeDto setTimeDto){
        Sleep sleep = sleepRepository.findByPk(sleepPk)
                .orElseThrow(()-> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        sleep.updateSetTime(DateTypeConverter.toZoneDateTime(setTimeDto.getSleepTime()),DateTypeConverter.toZoneDateTime(setTimeDto.getWakeTime()));
    }

    @Transactional
    public void updateActualWakeTime(SleepDto sleepDto){
        ZonedDateTime actualWakeTime = DateTypeConverter.toZoneDateTime(sleepDto.getActualWakeTime());
        Sleep sleep = sleepRepository.findByPk(sleepDto.getSleepPk())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        sleep.updateActualWakeTime(actualWakeTime);
    }

    @Transactional
    public void updateActualSleepTime(Long userPk){
        Sleep sleep = sleepRepository.findRecentSleepByUser(userPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        sleep.updateActualSleepTime();
    }


    public List<Sleep> findSleepsByUserAndDate(CalendarDto calendarDto){
        List<Sleep> sleepsByUserAndDate = sleepRepository.findSleepsByUserAndDate(calendarDto.getUserPk(), calendarDto.getDate());
        return sleepsByUserAndDate;
    }

    public Integer assessExperience(Long sleepPk){
        Sleep sleep = sleepRepository.findByPk(sleepPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        Integer plusExperience = sleep.assessExperience();
        return plusExperience;
    }



}
