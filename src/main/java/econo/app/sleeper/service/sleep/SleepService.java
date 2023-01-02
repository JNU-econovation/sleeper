package econo.app.sleeper.service.sleep;

import econo.app.sleeper.domain.Sleep;
import econo.app.sleeper.domain.User;
import econo.app.sleeper.repository.DiaryRepository;
import econo.app.sleeper.repository.SleepRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.util.DateTypeConverter;
import econo.app.sleeper.util.DateManager;
import econo.app.sleeper.web.calendar.CalendarDto;
import econo.app.sleeper.web.sleep.SetTimeDto;
import econo.app.sleeper.web.sleep.SleepDto;
import econo.app.sleeper.web.sleep.SetTimeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SleepService {

    private final SleepRepository sleepRepository;
    private final UserRepository userRepository;
    private final DiaryRepository diaryRepository;

    @Transactional
    public Sleep saveSetTime(SetTimeDto setTimeDto){
        User user = userRepository.findById(setTimeDto.getUserId()).get();
        Sleep sleep = setTimeDto.toEntity(DateTypeConverter.convertToZoneDateTime(setTimeDto.getSleepTime()),
                DateTypeConverter.convertToZoneDateTime(setTimeDto.getWakeTime()),user);
        sleepRepository.save(sleep);
        return sleep;
    }

    @Transactional
    public Sleep updateActualTime(SleepDto sleepDto){
        User user = userRepository.findById(sleepDto.getUserId()).get();
        SetTimeRequest setTimeRequest = SetTimeRequest.toDto(diaryRepository.findRecentDiaryByUser(user.getUserPk()).getWritingTime(),
                sleepDto.getActualRequestParam().getActualWakeTime());
        ZonedDateTime zonedDateTime1 = DateTypeConverter.convertToZoneDateTime(setTimeRequest.getSleepTime());
        ZonedDateTime zonedDateTime2 = DateTypeConverter.convertToZoneDateTime(setTimeRequest.getWakeTime());
        Sleep sleep = SetTimeRequest.toEntity(DateTypeConverter.convertToZoneDateTime(setTimeRequest.getSleepTime()),
                DateTypeConverter.convertToZoneDateTime(setTimeRequest.getWakeTime()));
        Sleep sleep2 = sleepRepository.findByPk(sleepDto.getSleepPk()).get();
        sleep2.updateActualTime(zonedDateTime1,zonedDateTime2);
        sleep2.updateSavingDate(DateManager.checkSavingDate(setTimeRequest.getSleepTime()));
        return sleep;
    }

//      해당 날짜에 해당하는 감사일기 찾기
    public List<Sleep> findSleepsByDate(CalendarDto calendarDto){
        String userId = calendarDto.getUserId();
        Long userPk = userRepository.findById(userId).get().getUserPk();
        return sleepRepository.findSleepsByDate(userPk,calendarDto.getDate());
    }

    // 설정한 수면시간이 자났을 때, 잠에 들 지 않았을 때
    @Transactional
    public void checkUserSleep(String userId){
        // 해당 회원의 가장 최근의 sleep 기록 중에서
        // 실제 수면 시작 시간이 없는 기록이 있으면
        // 케릭터의 상태 변경
        
    }

}
