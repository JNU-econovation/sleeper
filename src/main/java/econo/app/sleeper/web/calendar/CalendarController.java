package econo.app.sleeper.web.calendar;

import econo.app.sleeper.service.calendar.CalendarService;
import econo.app.sleeper.service.diary.DiaryService;
import econo.app.sleeper.web.common.CommonRequest;
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


@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "calendar", description = "캘린더 관련 API")
public class CalendarController {

    private final DiaryService diaryService;
    private final CalendarService calendarService;

    @Operation(summary = "api simple explain", description = "api specific explain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })

    @GetMapping("/calendar/{date}")
    public ResponseEntity<CalendarDateResponse> readCalendarByDate(@Valid CommonRequest commonRequest,
                                                                   @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date")LocalDate localDate){
        CalendarDateDto calendarDateDto = calendarService.readCalendarByDate(CalendarDto.of(commonRequest.getUserPk(), localDate));
        CalendarDateResponse calendarDateResponse = null;
        ArrayList<ZonedDateTime[]> sleepTimes = new ArrayList<>();
        if(calendarDateDto.getDiary().isPresent()){
            for(int i=0; i< calendarDateDto.getSleep().size(); i++){
                sleepTimes.add(new ZonedDateTime[]{calendarDateDto.getSleep().get(i).getAlarm().getSetSleepTime(),
                        calendarDateDto.getSleep().get(i).getAlarm().getSetWakeTime(), calendarDateDto.getSleep().get(i).getSavingDate().getSavingDateTime(), calendarDateDto.getSleep().get(i).getActualWakeTime()});
                calendarDateResponse = CalendarDateResponse.of(calendarDateDto.getDiary().get().getContent().getContent(), calendarDateDto.getDiary().get().getId(),sleepTimes.get(i), calendarDateDto.getScore()
                );
            }
            return new ResponseEntity<>(calendarDateResponse, HttpStatus.OK);
        }
        for(int i=0; i< calendarDateDto.getSleep().size(); i++){
            sleepTimes.add(new ZonedDateTime[]{calendarDateDto.getSleep().get(i).getAlarm().getSetSleepTime(),
                    calendarDateDto.getSleep().get(i).getAlarm().getSetWakeTime(), calendarDateDto.getSleep().get(i).getSavingDate().getSavingDateTime(), calendarDateDto.getSleep().get(i).getActualWakeTime()});
            calendarDateResponse = CalendarDateResponse.of(null, null, sleepTimes.get(i), calendarDateDto.getScore()
            );
        }
        return new ResponseEntity<>(calendarDateResponse,HttpStatus.OK);
    }


    @GetMapping("/calendar")
    public ResponseEntity<CalendarResponse> readCalendar(@ Valid CommonRequest commonRequest){
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Seoul"));
        CalendarResponse calendarResponse = calendarService.readCalendar(commonRequest.getUserPk(), localDate);
        return new ResponseEntity<>(calendarResponse,HttpStatus.OK);
    }


}
