package econo.app.sleeper.web.calendar;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@Builder
public class CalendarDto {

    private final Long userPk;
    private final LocalDate date;

    public static CalendarDto of(Long userPk,LocalDate date){
        return CalendarDto.builder()
                .userPk(userPk)
                .date(date)
                .build();
    }
}
