package econo.app.sleeper.web.calendar;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.ZonedDateTime;
import java.util.List;


@Getter
@RequiredArgsConstructor
@Builder
public class CalendarDateDto {

    private final List<List<ZonedDateTime>> sleepInfo;

    private final String content;

    private final Long score;

    public static CalendarDateDto of(List<List<ZonedDateTime>> sleepInfo, String content, Long score){
        return CalendarDateDto.builder()
                .sleepInfo(sleepInfo)
                .content(content)
                .score(score)
                .build();
    }
}
