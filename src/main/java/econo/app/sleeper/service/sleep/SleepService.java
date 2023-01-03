package econo.app.sleeper.service.sleep;

import econo.app.sleeper.domain.Character;
import econo.app.sleeper.domain.Sleep;
import econo.app.sleeper.domain.User;
import econo.app.sleeper.repository.CharacterRepository;
import econo.app.sleeper.repository.DiaryRepository;
import econo.app.sleeper.repository.SleepRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.util.DateTypeConverter;
import econo.app.sleeper.domain.DateTimeManager;
import econo.app.sleeper.util.SpeechBubbleKind;
import econo.app.sleeper.web.calendar.CalendarDto;
import econo.app.sleeper.web.sleep.ActualTimeDto;
import econo.app.sleeper.web.sleep.SetTimeDto;
import econo.app.sleeper.web.sleep.SleepDto;
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
    private final DiaryRepository diaryRepository;

    private final CharacterRepository characterRepository;

    @Transactional
    public Sleep saveSetTime(SetTimeDto setTimeDto){
        User user = userRepository.findById(setTimeDto.getUserId()).get();
        Sleep sleep = setTimeDto.toEntity(DateTypeConverter.convertToZoneDateTime(setTimeDto.getSleepTime()),
                DateTypeConverter.convertToZoneDateTime(setTimeDto.getWakeTime()),user);
        sleepRepository.save(sleep);
        return sleep;
    }

    @Transactional
    public void updateActualTime(SleepDto sleepDto){
        User user = userRepository.findById(sleepDto.getUserId()).get();
        ActualTimeDto actualTimeDto = ActualTimeDto.of(diaryRepository.findRecentDiaryByUser(user.getUserPk()).getWritingTime(),
                sleepDto.getActualWakeTime());
        ZonedDateTime actualSleepTime = DateTypeConverter.convertToZoneDateTime(actualTimeDto.getActualSleepTime());
        ZonedDateTime actualWakeTime = DateTypeConverter.convertToZoneDateTime(actualTimeDto.getActualWakeTime());
        Sleep sleep = sleepRepository.findByPk(sleepDto.getSleepPk()).get();
        sleep.updateActualTime(actualSleepTime,actualWakeTime);
        DateTimeManager dateTimeManager = new DateTimeManager();
        sleep.updateSavingDate(dateTimeManager.giveSavingDate());
    }


    public List<Sleep> findSleepsByDate(CalendarDto calendarDto){
        String userId = calendarDto.getUserId();
        Long userPk = userRepository.findById(userId).get().getUserPk();
        return sleepRepository.findSleepsByDate(userPk,calendarDto.getDate());
    }


    @Transactional
    public void checkOverSetSleep(String userId){
        User user = userRepository.findById(userId).get();
        Sleep recentSleepByUser = sleepRepository.findRecentSleepByUser(user.getUserPk());

        if(recentSleepByUser.getActualSleepTime() == null){
            Character character = characterRepository.findById(userId).get();
            character.updateCharacter(SpeechBubbleKind.SLEEP.message());
        }
    }

}
