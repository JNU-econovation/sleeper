package econo.app.sleeper.web.calendar;

import econo.app.sleeper.service.calendar.CalendarService;
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


@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "calendar", description = "캘린더 관련 API")
public class CalendarController {

    private final CalendarService calendarService;

    @Operation(summary = "api simple explain", description = "api specific explain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })

    @GetMapping("/calendar/{date}")
    public ResponseEntity<CalendarDateDto> readCalendarByDate(@Valid CommonRequest commonRequest,
                                                                   @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date")LocalDate nowDate){
        CalendarDateDto calendarDateDto = calendarService.readCalendarByDate(CalendarDto.of(commonRequest.getUserPk(), nowDate));
        return new ResponseEntity<>(calendarDateDto,HttpStatus.OK);
    }


    @GetMapping("/calendar")
    public ResponseEntity<CalendarResponse> readCalendar(@ Valid CalendarRequest calendarRequest){
        CalendarResponse calendarResponse = calendarService.readCalendar(calendarRequest);
        return new ResponseEntity<>(calendarResponse,HttpStatus.OK);
    }


}
