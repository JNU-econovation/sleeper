package econo.app.sleeper.web.diary;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@Builder
public class DiaryResponseForm {

    private final String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDate localDate;

    public static DiaryResponseForm of(String content, LocalDate localDate){
        return DiaryResponseForm.builder()
                .content(content)
                .localDate(localDate)
                .build();
    }
}
