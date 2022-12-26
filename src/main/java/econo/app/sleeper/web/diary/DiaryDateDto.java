package econo.app.sleeper.web.diary;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@RequiredArgsConstructor
@Builder
public class DiaryDateDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate localDate;

    private final String userId;

    public static DiaryDateDto of(String userId, LocalDate localDate){
        return DiaryDateDto.builder()
                .userId(userId)
                .localDate(localDate)
                .build();
    }
}
