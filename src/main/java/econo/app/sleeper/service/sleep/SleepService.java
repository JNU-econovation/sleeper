package econo.app.sleeper.service.sleep;

import econo.app.sleeper.domain.Diary;
import econo.app.sleeper.domain.Sleep;
import econo.app.sleeper.domain.User;
import econo.app.sleeper.repository.DiaryRepository;
import econo.app.sleeper.repository.SleepRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.util.Converter;
import econo.app.sleeper.util.DateJudgementUtil;
import econo.app.sleeper.web.calendar.CalendarDto;
import econo.app.sleeper.web.diary.DiaryDateDto;
import econo.app.sleeper.web.sleep.SleepDto;
import econo.app.sleeper.web.sleep.TimeRequestDto;
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
    public Sleep saveSetTime(TimeRequestDto timeRequestDto){
        Sleep sleep = TimeRequestDto.toEntity(Converter.convertToZoneDateTime(timeRequestDto.getSleepTime()),
                Converter.convertToZoneDateTime(timeRequestDto.getWakeTime()));
        sleepRepository.save(sleep);
        return sleep;
    }

    @Transactional
    public Sleep updateActualTime(SleepDto sleepDto){
        User user = userRepository.findById(sleepDto.getUserId()).get();
        TimeRequestDto timeRequestDto = TimeRequestDto.toDto(diaryRepository.findRecentDiaryByUser(user.getUserPk()).getWritingTime(),
                sleepDto.getActualRequestParam().getActualWakeTime());
        ZonedDateTime zonedDateTime1 = Converter.convertToZoneDateTime(timeRequestDto.getSleepTime());
        ZonedDateTime zonedDateTime2 = Converter.convertToZoneDateTime(timeRequestDto.getWakeTime());
        Sleep sleep = TimeRequestDto.toEntity(Converter.convertToZoneDateTime(timeRequestDto.getSleepTime()),
                Converter.convertToZoneDateTime(timeRequestDto.getWakeTime()));
        Sleep sleep2 = sleepRepository.findByPk(sleepDto.getSleepPk()).get();
        sleep2.updateActualTime(zonedDateTime1,zonedDateTime2);
        sleep2.updateSavingDate(DateJudgementUtil.checkSavingDate(timeRequestDto.getSleepTime()));
        return sleep;
    }

//      해당 날짜에 해당하는 감사일기 찾기
    public List<Sleep> findSleepsByDate(CalendarDto calendarDto){
        String userId = calendarDto.getUserId();
        Long userPk = userRepository.findById(userId).get().getUserPk();
        return sleepRepository.findSleepsByDate(userPk,calendarDto.getDate());
    }

}
