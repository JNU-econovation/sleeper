package econo.app.sleeper.web.calendar;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@Builder
public class CalendarDto {

    private final String userId;
    private final LocalDate date;

    public static CalendarDto of(String userId,LocalDate date){
        return CalendarDto.builder()
                .userId(userId)
                .date(date)
                .build();
    }
}
