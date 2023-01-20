package econo.app.sleeper.web.calendar;

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
    private final Integer score;


    public static CalendarDateResponse of(String content, Long diaryPk, List<ZonedDateTime> setSleepTime, List<ZonedDateTime> setWakeTime, List<ZonedDateTime> actualSleepTime, List<ZonedDateTime> actualWakeTime,Integer score){
        return CalendarDateResponse.builder()
                .content(content)
                .diaryPk(diaryPk)
                .setSleepTime(setSleepTime)
                .setWakeTime(setWakeTime)
                .actualSleepTime(actualSleepTime)
                .actualWakeTime(actualWakeTime)
                .score(score)
                .build();
    }



}


