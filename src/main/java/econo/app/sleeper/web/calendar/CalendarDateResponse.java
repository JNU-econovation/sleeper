package econo.app.sleeper.web.calendar;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@RequiredArgsConstructor
@Builder
public class CalendarDateResponse {

    private final String content;
    private final Long diaryPk;
    private final ZonedDateTime[] sleepTimes;
    private final Integer score;


    public static CalendarDateResponse of(String content, Long diaryPk, ZonedDateTime[] sleepTimes,Integer score){
        return CalendarDateResponse.builder()
                .content(content)
                .diaryPk(diaryPk)
                .sleepTimes(sleepTimes)
                .score(score)
                .build();
    }




}


