package econo.app.sleeper.web.calendar;

import econo.app.sleeper.web.common.Link;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;


@Getter
@RequiredArgsConstructor
@Builder
public class CalendarDateResponse {

    private final String content;
    private final Long diaryPk;
    private final ZonedDateTime setSleepTime;
    private final ZonedDateTime setWakeTime;
    private final ZonedDateTime actualSleepTime;
    private final ZonedDateTime actualWakeTime;
    private final Link link;

    public static CalendarDateResponse of(String content, Long diaryPk, ZonedDateTime setSleepTime, ZonedDateTime setWakeTime, ZonedDateTime actualSleepTime, ZonedDateTime actualWakeTime
    , Link link){
        return CalendarDateResponse.builder()
                .content(content)
                .diaryPk(diaryPk)
                .setSleepTime(setSleepTime)
                .setWakeTime(setWakeTime)
                .actualSleepTime(actualSleepTime)
                .actualWakeTime(actualWakeTime)
                .link(link)
                .build();
    }



}


