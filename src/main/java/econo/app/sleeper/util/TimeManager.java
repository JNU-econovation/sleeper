package econo.app.sleeper.util;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimeManager {

    public static List<LocalTime> suggestWakeTime(LocalTime localTime){
        LocalTime localTime1 = localTime.plusHours(6);
        LocalTime localTime2 = localTime1.plusHours(1).plusMinutes(30);
        LocalTime localTime3 = localTime2.plusHours(1).plusMinutes(30);
        LocalTime localTime4 = localTime3.plusHours(1).plusMinutes(30);
        List<LocalTime> suggestedWakeTimes = Arrays.asList(localTime1,localTime2,localTime3,localTime4);
        return suggestedWakeTimes;
    }

}
