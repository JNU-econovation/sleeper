package econo.app.sleeper.util;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class DateTypeConverter {

    public static ZonedDateTime toZoneDateTime(LocalDateTime localDateTime){
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Seoul"));
        return zonedDateTime;
    }
}
