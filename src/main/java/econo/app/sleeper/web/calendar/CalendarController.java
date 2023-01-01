package econo.app.sleeper.web.calendar;

import econo.app.sleeper.domain.Diary;
import econo.app.sleeper.domain.Sleep;
import econo.app.sleeper.service.diary.DiaryService;
import econo.app.sleeper.service.sleep.SleepService;
import econo.app.sleeper.web.Link;
import econo.app.sleeper.web.diary.DiaryDateDto;
import econo.app.sleeper.web.login.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CalendarController {

    private final DiaryService diaryService;
    private final SleepService sleepService;

    @GetMapping("/calendar/{date}")
    public ResponseEntity<List<CalendarDateResponse>> readCalendarOfDate(@SessionAttribute("loginUser") Object loginUser,
                                                                         @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date")LocalDate localDate){
        LoginUser loginUser1 = (LoginUser) loginUser;
        String userId = loginUser1.getUserId();
        List<Diary> diariesByDate = diaryService.findDiariesByDate(DiaryDateDto.of(userId, localDate));
        List<Sleep> sleepsByDate = sleepService.findSleepsByDate(CalendarDto.of(userId, localDate));
        List<CalendarDateResponse> calendarDateRespons = new ArrayList<>();
        for(int i=0;i<diariesByDate.size();i++){
            calendarDateRespons.add(CalendarDateResponse.of(diariesByDate.get(i).getContent(),diariesByDate.get(i).getDiaryPk(), sleepsByDate.get(i).getSetSleepTime()
                    , sleepsByDate.get(i).getSetWakeTime(), sleepsByDate.get(i).getActualSleepTime(), sleepsByDate.get(i).getActualWakeTime()
                    ,Link.of("diary", "/diaries/" + diariesByDate.get(i).getDiaryPk(), "GET", List.of("application/x-www-form-urlenceded"))));
        }
        return new ResponseEntity<>(calendarDateRespons, HttpStatus.OK);
    }

    @GetMapping("/calendar")
    public ResponseEntity<CalendarResponse> readCalendar(@SessionAttribute Object loginUser){
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Seoul"));
        LoginUser loginUser1 = (LoginUser) loginUser;
        List<Diary> diaries = diaryService.findDiariesBetWeenDates(DiaryDateDto.of(loginUser1.getUserId(), localDate));
        List<LocalDate> localDates = new ArrayList<>();
        for(Diary d : diaries){
            localDates = List.of(d.getLocalDate());
        }
        CalendarResponse calendarResponse = CalendarResponse.of(localDates);
        return new ResponseEntity<>(calendarResponse,HttpStatus.OK);
    }


}
