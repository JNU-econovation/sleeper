package econo.app.sleeper.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Getter
@Setter
@ToString
public class DateManager {

    private DateManager(){
        // 사용 x
    }

    public static LocalDate checkSavingDate(LocalDateTime nowTime) {
        int dayOfMonth = nowTime.getDayOfMonth();
        int hour = nowTime.getHour();
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

    public static LocalDate giveEndDate(LocalDate localDate){
        YearMonth month = YearMonth.from(localDate);
        return month.atEndOfMonth();
    }


}
