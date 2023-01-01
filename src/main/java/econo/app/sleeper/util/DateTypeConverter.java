package econo.app.sleeper.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTypeConverter {

    public static ZonedDateTime convertToZoneDateTime(LocalDateTime localDateTime){
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Seoul"));
        return zonedDateTime;
    }
}
