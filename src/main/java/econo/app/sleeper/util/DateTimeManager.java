package econo.app.sleeper.util;

import lombok.Getter;

import java.time.*;
import java.util.Arrays;
import java.util.List;

@Getter
public class DateTimeManager {

    public static LocalDate giveEndDate(LocalDate localDate){
        YearMonth month = YearMonth.from(localDate);
        return month.atEndOfMonth();
    }

    public static List<LocalTime> suggestWakeTime(LocalTime localTime){
        LocalTime localTime1 = localTime.plusHours(6);
        LocalTime localTime2 = localTime1.plusHours(1).plusMinutes(30);
        LocalTime localTime3 = localTime2.plusHours(1).plusMinutes(30);
        LocalTime localTime4 = localTime3.plusHours(1).plusMinutes(30);
        List<LocalTime> suggestedWakeTimes = Arrays.asList(localTime1,localTime2,localTime3,localTime4);
        return suggestedWakeTimes;
    }


}
