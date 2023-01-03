package econo.app.sleeper.util;

import econo.app.sleeper.domain.DateTimeManager;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.*;

public class DateTimeManagerTest {

    @Test
    public void checkSavingDate1() {

        LocalDateTime testTime = LocalDateTime.of(2022,12,1,3,11);

        ZonedDateTime.of(testTime,ZoneId.of("Asia/Seoul"));

        LocalDate localDate = DateTimeManager.giveSavingDate(testTime);

        LocalDate expectedTime = LocalDate.of(2022,11,30);

        Assertions.assertThat(localDate).isEqualTo(expectedTime);


    }

    @Test
    public void checkSavingDate2() {

        LocalDateTime testTime = LocalDateTime.of(2022,12,22,3,11);

        System.out.println("testTime = " + testTime);

        LocalDate localDate = DateTimeManager.giveSavingDate(testTime);

        LocalDate expectedTime = LocalDate.of(2022,12,21);

        Assertions.assertThat(localDate).isEqualTo(expectedTime);

    }



}