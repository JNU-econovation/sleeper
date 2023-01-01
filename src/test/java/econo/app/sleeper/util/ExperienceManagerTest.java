package econo.app.sleeper.util;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

public class ExperienceManagerTest {


    @Test
    public void assessExperience(){
        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(LocalDateTime.of(2023,01,02,1,36), ZoneId.of("Asia/Seoul"));
        ZonedDateTime zonedDateTime2 = ZonedDateTime.of(LocalDateTime.of(2023, 01, 02, 9, 00), ZoneId.of("Asia/Seoul"));
        ZonedDateTime zonedDateTime3 = ZonedDateTime.of(LocalDateTime.of(2023, 01, 02, 0, 10), ZoneId.of("Asia/Seoul"));
        ZonedDateTime zonedDateTime4 = ZonedDateTime.of(LocalDateTime.of(2023, 01, 02, 7, 00), ZoneId.of("Asia/Seoul"));

        Integer experience = ExperienceManager.assessExperience(zonedDateTime3, zonedDateTime4, zonedDateTime1, zonedDateTime2);
        System.out.println("experience = " + experience);
    }

}