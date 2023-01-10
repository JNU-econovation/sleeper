package econo.app.sleeper.service.sleep;

import econo.app.sleeper.domain.sleep.Sleep;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.repository.CharacterRepository;
import econo.app.sleeper.repository.DiaryRepository;
import econo.app.sleeper.repository.SleepRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.util.DateTypeConverter;
import econo.app.sleeper.web.calendar.CalendarDto;
import econo.app.sleeper.web.sleep.SetTimeDto;
import econo.app.sleeper.web.sleep.SleepDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SleepService {

    private final SleepRepository sleepRepository;
    private final UserRepository userRepository;

    @Transactional
    public Sleep saveSetTime(String userId){
        User user = userRepository.findById(userId).get();
        List<LocalDateTime> localDateTimes = user.getGoalTime().toLocalDateTime();
        SetTimeDto setTimeDto = SetTimeDto.of(localDateTimes.get(0), localDateTimes.get(1), userId);
        Sleep sleep = setTimeDto.toEntity(user);
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
    public Sleep updateActualSleepTime(Long userPk){
        User user = userRepository.find(userPk).get();
        Sleep sleep = sleepRepository.findRecentSleepByUser(userPk);
        sleep.updateActualSleepTime();
        return sleep;
    }


    public List<Sleep> findSleepsByUserAndDate(CalendarDto calendarDto){
        String userId = calendarDto.getUserId();
        Long userPk = userRepository.findById(userId).get().getUserPk();
        return sleepRepository.findSleepsByUserAndDate(userPk,calendarDto.getDate());
    }


}
