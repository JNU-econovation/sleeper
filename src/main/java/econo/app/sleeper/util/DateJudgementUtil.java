package econo.app.sleeper.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

@Getter
@Setter
@ToString
public class DateJudgementUtil {

    private DateJudgementUtil(){
        // 사용 x
    }

    public static LocalDate checkSavingDate(LocalDateTime nowTime) {
        System.out.println("nowTime = " + nowTime);
        int dayOfMonth = nowTime.getDayOfMonth();
        System.out.println("dayOfMonth = " + dayOfMonth);
        int hour = nowTime.getHour();
        System.out.println("hour = " + hour);

        LocalDate localDate = nowTime.toLocalDate();

        if (dayOfMonth == 1) {
            if (hour >= 0 && hour <= 5) {
                LocalDate localDate1 = localDate.minusMonths(1);
                return localDate1.withDayOfMonth(localDate1.lengthOfMonth());
            }
        } else {
            if (hour >= 0 && hour <= 5) {
                return localDate.minusDays(1);
            }
        }

        return localDate;
    }


}
