package econo.app.sleeper.web.diary;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Getter
@RequiredArgsConstructor
@Builder
public class DiaryFindDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate localDate;

    private final String userId;

    public static DiaryFindDto of(String userId, LocalDate localDate){
        return DiaryFindDto.builder()
                .userId(userId)
                .localDate(localDate)
                .build();
    }

}
