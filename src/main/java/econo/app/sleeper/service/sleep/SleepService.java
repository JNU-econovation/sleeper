package econo.app.sleeper.service.sleep;

import econo.app.sleeper.domain.sleep.Sleep;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.repository.SleepRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.util.DateTypeConverter;
import econo.app.sleeper.web.calendar.CalendarDto;
import econo.app.sleeper.web.sleep.SetTimeDto;
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

    @Transactional
    public void updateSetTime(Long sleepPk,SetTimeDto setTimeDto){
        Sleep sleep = sleepRepository.findByPk(sleepPk).get();
        sleep.updateSetTime(DateTypeConverter.toZoneDateTime(setTimeDto.getSleepTime()),DateTypeConverter.toZoneDateTime(setTimeDto.getWakeTime()));
    }

    @Transactional
    public void updateActualWakeTime(SleepDto sleepDto){
        ZonedDateTime actualWakeTime = DateTypeConverter.toZoneDateTime(sleepDto.getActualWakeTime());
        Sleep sleep = sleepRepository.findByPk(sleepDto.getSleepPk()).get();
        sleep.updateActualWakeTime(actualWakeTime);
    }

    @Transactional
    public void updateActualSleepTime(Long userPk){
        Sleep sleep = sleepRepository.findRecentSleepByUser(userPk);
        sleep.updateActualSleepTime();
    }


    public List<Sleep> findSleepsByUserAndDate(CalendarDto calendarDto){
        User user = userRepository.find(calendarDto.getUserPk()).get();
        Stream<Sleep> sleepStream = user.getSleeps().stream()
                .filter(s -> s.getSavingDate().getSavingDate().isEqual(calendarDto.getDate()));
        return sleepStream.collect(Collectors.toList());
    }

    public Integer assessExperience(Long sleepPk){
        Sleep sleep = sleepRepository.findByPk(sleepPk).get();
        Integer plusExperience = sleep.assessExperience();
        return plusExperience;
    }



}
