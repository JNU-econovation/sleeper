package econo.app.sleeper.domain.common;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Component
@Primary
public class FixedDatePolicy implements DatePolicy{

    private Integer standardTime = 5;

    @Override
    public LocalDate decideDate(ZonedDateTime savedDateTime) {
        int dayOfMonth = savedDateTime.getDayOfMonth();
        int hour = savedDateTime.getHour();
        LocalDate savedDate = savedDateTime.toLocalDate();
        if (hour >= 0 && hour <= standardTime) {
            if (dayOfMonth == 1) {
                LocalDate changedDate = savedDate.minusMonths(1);
                LocalDate decidedDate = changedDate.withDayOfMonth(changedDate.lengthOfMonth());
                return decidedDate;
            }else{
                LocalDate decidedDate = savedDate.minusDays(1);
                return decidedDate;
            }
        }
        return savedDate;
    }

}
