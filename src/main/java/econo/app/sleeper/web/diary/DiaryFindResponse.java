package econo.app.sleeper.web.diary;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@Builder
public class DiaryFindResponse {

    private final String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDate savingDate;

    public static DiaryFindResponse of(String content, LocalDate savingDate){
        return DiaryFindResponse.builder()
                .content(content)
                .savingDate(savingDate)
                .build();
    }
}
