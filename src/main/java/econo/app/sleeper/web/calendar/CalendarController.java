package econo.app.sleeper.web.calendar;

import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.domain.sleep.Sleep;
import econo.app.sleeper.service.diary.DiaryService;
import econo.app.sleeper.service.sleep.SleepService;
import econo.app.sleeper.web.common.CommonRequest;
import econo.app.sleeper.web.common.Link;
import econo.app.sleeper.web.diary.DiaryFindDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "calendar", description = "캘린더 관련 API")
public class CalendarController {

    private final DiaryService diaryService;
    private final SleepService sleepService;

    @Operation(summary = "api simple explain", description = "api specific explain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })

    @GetMapping("/calendar/{date}")
    public ResponseEntity<CalendarDateResponse> readCalendarOfDate(@Valid CommonRequest commonRequest,
                                                                         @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date")LocalDate localDate){
        Long userPk = commonRequest.getUserPk();
        Diary diaryByDate = diaryService.findDiaryByDate(DiaryFindDto.of(userPk, localDate));
        List<Sleep> sleepsByDate = sleepService.findSleepsByUserAndDate(CalendarDto.of(userPk, localDate));
        CalendarDateResponse calendarDateResponse = null;
        for(int i=0; i< sleepsByDate.size(); i++){
            calendarDateResponse = CalendarDateResponse.of(diaryByDate.getContent().getContent(), diaryByDate.getId(), List.of(sleepsByDate.get(i).getSetTime().getSetSleepTime()),
                    List.of(sleepsByDate.get(i).getSetTime().getSetWakeTime()), List.of(sleepsByDate.get(i).getSavingDate().getSavingDateTime()), List.of(sleepsByDate.get(i).getActualWakeTime()));
        }
        return new ResponseEntity<>(calendarDateResponse, HttpStatus.OK);
    }

    @GetMapping("/calendar")
    public ResponseEntity<CalendarResponse> readCalendar(@ Valid CommonRequest commonRequest){
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Seoul"));
        List<Diary> diaries = diaryService.findDiariesBetWeenDates(DiaryFindDto.of(commonRequest.getUserPk(), localDate));
        List<LocalDate> savingDates = new ArrayList<>();
        for(Diary d : diaries){
            savingDates = List.of(d.getSavingDate().getSavingDate());
        }
        CalendarResponse calendarResponse = CalendarResponse.of(savingDates);
        return new ResponseEntity<>(calendarResponse,HttpStatus.OK);
    }


}
