package econo.app.sleeper.web.calendar;

import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.domain.sleep.Sleep;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
@Builder
public class CalendarDateDto {

    private final List<Sleep> sleep;

    private final Optional<Diary> diary;

    private final Integer score;

    public static CalendarDateDto of(List<Sleep> sleep, Optional<Diary> diary, Integer score){
        return CalendarDateDto.builder()
                .diary(diary)
                .sleep(sleep)
                .score(score)
                .build();
    }
}
