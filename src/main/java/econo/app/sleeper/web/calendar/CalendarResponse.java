package econo.app.sleeper.web.calendar;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@RequiredArgsConstructor
@Getter
@Builder
public class CalendarResponse {

    private final List<LocalDate> localDateList;

    public static CalendarResponse of(List<LocalDate> savingDates){
        return CalendarResponse.builder()
                .localDateList(savingDates)
                .build();
    }
}
