package econo.app.sleeper.domain;

import lombok.Getter;

import java.time.*;

@Getter
public class DateTimeManager {

    private final LocalDateTime nowDateTime;

    public DateTimeManager() {
        LocalDateTime DateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        this.nowDateTime = DateTime;
    }

    public LocalDate giveSavingDate() {
        int dayOfMonth = nowDateTime.getDayOfMonth();
        int hour = nowDateTime.getHour();
        LocalDate localDate = nowDateTime.toLocalDate();

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
