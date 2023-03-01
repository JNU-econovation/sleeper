package econo.app.sleeper.service.calendar;


import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.domain.sleep.Sleep;
import econo.app.sleeper.exception.RestApiException;
import econo.app.sleeper.exception.error.CommonErrorCode;
import econo.app.sleeper.repository.DiaryRepository;
import econo.app.sleeper.repository.SleepRepository;
import econo.app.sleeper.web.calendar.CalendarDateDto;
import econo.app.sleeper.web.calendar.CalendarDto;
import econo.app.sleeper.web.calendar.CalendarRequest;
import econo.app.sleeper.web.calendar.CalendarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CalendarService {

    private final DiaryRepository diaryRepository;

    private final SleepRepository sleepRepository;

    public CalendarDateDto readCalendarByDate(CalendarDto calendarDto){
        List<Sleep> sleeps = sleepRepository.findSleepsByUserAndDate(calendarDto.getUserPk(), calendarDto.getDate());
        Diary diary = diaryRepository.findDiaryByDate(calendarDto.getUserPk(), calendarDto.getDate())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        Long score = 0L;
        List<List<ZonedDateTime>> sleepInfos = new ArrayList<>();
        for(Sleep s : sleeps){
            score += s.evaluateSleep();
            sleepInfos.add(s.getSleepInfo());
        }
        CalendarDateDto calendarDateDto = CalendarDateDto.of(sleepInfos, diary.getContent(), score);
        return calendarDateDto;
    }

    public CalendarResponse readCalendar(CalendarRequest calendarRequest){
        List<Sleep> sleepsBetweenDates = sleepRepository.findSleepsBetweenDates(calendarRequest.getUserPk(), calendarRequest.getNowDate().withDayOfMonth(1),
                calendarRequest.getNowDate().withDayOfMonth(calendarRequest.getNowDate().lengthOfMonth()));
        List<LocalDate> sleepDates = new ArrayList<>();
        for(Sleep s : sleepsBetweenDates){
            sleepDates = List.of(s.getSleepDate());
        }
        CalendarResponse calendarResponse = CalendarResponse.of(sleepDates);
        return calendarResponse;
    }
}
