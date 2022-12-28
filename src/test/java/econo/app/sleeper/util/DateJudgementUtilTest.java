package econo.app.sleeper.util;

import econo.app.sleeper.web.login.SessionConst;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.test.context.TestExecutionListeners;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Enumeration;

import static org.junit.Assert.*;

public class DateJudgementUtilTest {

    @Test
    public void checkSavingDate1() {

        LocalDateTime testTime = LocalDateTime.of(2022,12,1,3,11);

        ZonedDateTime.of(testTime,ZoneId.of("Asia/Seoul"));

        LocalDate localDate = DateJudgementUtil.checkSavingDate(testTime);

        LocalDate expectedTime = LocalDate.of(2022,11,30);

        Assertions.assertThat(localDate).isEqualTo(expectedTime);


    }

    @Test
    public void checkSavingDate2() {

        LocalDateTime testTime = LocalDateTime.of(2022,12,22,3,11);

        System.out.println("testTime = " + testTime);

        LocalDate localDate = DateJudgementUtil.checkSavingDate(testTime);

        LocalDate expectedTime = LocalDate.of(2022,12,21);

        Assertions.assertThat(localDate).isEqualTo(expectedTime);

    }



}