package econo.app.sleeper.domain.common;

import lombok.Getter;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Embeddable
@Getter
public class SavingDate {

    @Column(name = "SAVING_DATE", columnDefinition = "DATE")
    private LocalDate savingDate;

    @Column(name = "SAVING_DATE_TIME", columnDefinition = "timeStamp")
    private ZonedDateTime savingDateTime;

    public SavingDate(){
        this.savingDate = getClassOfSavingDate();
        this.savingDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    }
    private LocalDate getClassOfSavingDate() {
        LocalDateTime nowDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));;
        int dayOfMonth = nowDateTime.getDayOfMonth();
        int hour = nowDateTime.getHour();
        LocalDate localDate = nowDateTime.toLocalDate();
        if (dayOfMonth == 1) {
            if (hour >= 0 && hour <= 5) {
                LocalDate localDate1 = localDate.minusMonths(1);
                savingDate = localDate1.withDayOfMonth(localDate1.lengthOfMonth());
                return savingDate;
            }
        } else {
            if (hour >= 0 && hour <= 5) {
                savingDate = localDate.minusDays(1);
                return savingDate;
            }
        }
        savingDate = localDate;
        return savingDate;
    }

}
