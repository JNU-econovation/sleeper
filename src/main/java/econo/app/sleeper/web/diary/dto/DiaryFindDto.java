package econo.app.sleeper.web.diary.dto;

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

    private final Long userPk;

    public static DiaryFindDto of(Long userPk, LocalDate localDate){
        return DiaryFindDto.builder()
                .userPk(userPk)
                .localDate(localDate)
                .build();
    }

}
