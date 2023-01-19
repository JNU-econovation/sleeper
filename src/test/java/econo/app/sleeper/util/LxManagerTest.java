package econo.app.sleeper.util;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class LxManagerTest {


    @Test
    public void assessExperience(){
        ZonedDateTime setSleepTime = ZonedDateTime.of(LocalDateTime.of(2023,01,02,1,00), ZoneId.of("Asia/Seoul"));
        ZonedDateTime setWakeTime = ZonedDateTime.of(LocalDateTime.of(2023, 01, 02, 9, 00), ZoneId.of("Asia/Seoul"));
        ZonedDateTime actualSleepTime = ZonedDateTime.of(LocalDateTime.of(2023, 01, 02, 2, 00), ZoneId.of("Asia/Seoul"));
        ZonedDateTime actualWakeTime = ZonedDateTime.of(LocalDateTime.of(2023, 01, 02, 7, 00), ZoneId.of("Asia/Seoul"));
    }



}