package econo.app.sleeper.util;

import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class NotificationTest {

    @Test
    public void test(){
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println("now = " + now);
    }
}