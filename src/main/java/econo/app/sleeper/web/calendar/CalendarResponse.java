package econo.app.sleeper.web.calendar;

import econo.app.sleeper.domain.common.SavingDate;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
@Getter
@Builder
public class CalendarResponse {

    private final List<SavingDate> localDateList;

    public static CalendarResponse of(List<SavingDate> savingDates){
        return CalendarResponse.builder()
                .localDateList(savingDates)
                .build();
    }
}
