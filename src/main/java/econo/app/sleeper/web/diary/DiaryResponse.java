package econo.app.sleeper.web.diary;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class DiaryResponse {

    private final Long diaryPk;
    private final String content;
    private final LocalDate savingDate;
    private final LocalDateTime writingTime;

    public static DiaryResponse of(Long diaryPk){
        return DiaryResponse.builder()
                .diaryPk(diaryPk)
                .build();
    }

    public static DiaryResponse of(String content, LocalDate savingDate, LocalDateTime writingTime){
        return DiaryResponse.builder()
                .content(content)
                .savingDate(savingDate)
                .writingTime(writingTime)
                .build();
    }
}
