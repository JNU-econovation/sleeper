package econo.app.sleeper.web.diary;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class DiaryResponse {

    private final Long diaryPk;

    public static DiaryResponse of(Long diaryPk){
        return DiaryResponse.builder()
                .diaryPk(diaryPk)
                .build();
    }
}
