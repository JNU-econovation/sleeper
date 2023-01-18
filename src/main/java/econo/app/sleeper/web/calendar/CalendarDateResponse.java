package econo.app.sleeper.web.calendar;

import econo.app.sleeper.web.common.Link;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;


@Getter
@RequiredArgsConstructor
@Builder
public class CalendarDateResponse {

    private final String content;
    private final Long diaryPk;
    private final List<ZonedDateTime> setSleepTime;
    private final List<ZonedDateTime> setWakeTime;
    private final List<ZonedDateTime> actualSleepTime;
    private final List<ZonedDateTime> actualWakeTime;

    public static CalendarDateResponse of(String content, Long diaryPk, List<ZonedDateTime> setSleepTime, List<ZonedDateTime> setWakeTime, List<ZonedDateTime> actualSleepTime, List<ZonedDateTime> actualWakeTime){
        return CalendarDateResponse.builder()
                .content(content)
                .diaryPk(diaryPk)
                .setSleepTime(setSleepTime)
                .setWakeTime(setWakeTime)
                .actualSleepTime(actualSleepTime)
                .actualWakeTime(actualWakeTime)
                .build();
    }



}


