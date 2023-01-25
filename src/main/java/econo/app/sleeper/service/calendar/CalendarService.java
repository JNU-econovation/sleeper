package econo.app.sleeper.service.calendar;


import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.domain.sleep.Sleep;
import econo.app.sleeper.repository.DiaryRepository;
import econo.app.sleeper.repository.SleepRepository;
import econo.app.sleeper.service.diary.DiaryService;
import econo.app.sleeper.service.sleep.SleepService;
import econo.app.sleeper.web.calendar.CalendarDateDto;
import econo.app.sleeper.web.calendar.CalendarDto;
import econo.app.sleeper.web.calendar.CalendarResponse;
import econo.app.sleeper.web.sleep.SleepScoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CalendarService {

    private final DiaryRepository diaryRepository;

    private final SleepRepository sleepRepository;

    private final SleepService sleepService;


    public CalendarDateDto readCalendarByDate(CalendarDto calendarDto){
        List<Sleep> sleepsByUserAndDate = sleepRepository.findSleepsByUserAndDate(calendarDto.getUserPk(), calendarDto.getDate());
        List<Long> sleepPks = new ArrayList<>();
        for(Sleep s : sleepsByUserAndDate){
            sleepPks.add(s.getId());
        }
        SleepScoreDto sleepScoreDto = SleepScoreDto.of(sleepPks);
        Integer score = sleepService.assessScore(sleepScoreDto);
        Optional<Diary> diaryByDate = diaryRepository.findDiaryByDate(calendarDto.getUserPk(), calendarDto.getDate());
        CalendarDateDto calendarDateDto = CalendarDateDto.of(sleepsByUserAndDate, diaryByDate, score);
        return calendarDateDto;
    }

    public CalendarResponse readCalendar(Long userPk, LocalDate localDate){
        List<Sleep> sleepsBetweenDates = sleepRepository.findSleepsBetweenDates(userPk, localDate.withDayOfMonth(1),
                localDate.withDayOfMonth(localDate.lengthOfMonth()));
        List<LocalDate> savingDates = new ArrayList<>();
        for(Sleep s : sleepsBetweenDates){
            savingDates = List.of(s.getSavingDate().getSavingDate());
        }
        CalendarResponse calendarResponse = CalendarResponse.of(savingDates);
        return calendarResponse;
    }
}
