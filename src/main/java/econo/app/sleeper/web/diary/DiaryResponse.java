package econo.app.sleeper.web.diary;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class DiaryResponse {

    private final Long diaryPk;
    private final String content;
    private final LocalDate savingDate;
    private final ZonedDateTime writingTime;

    public static DiaryResponse of(Long diaryPk){
        return DiaryResponse.builder()
                .diaryPk(diaryPk)
                .build();
    }

    public static DiaryResponse of(String content, LocalDate savingDate, ZonedDateTime writingTime){
        return DiaryResponse.builder()
                .content(content)
                .savingDate(savingDate)
                .writingTime(writingTime)
                .build();
    }
}
