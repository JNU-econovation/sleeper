package econo.app.sleeper.web.diary;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@Getter
public class DiarySaveResponse {

    private final Long diaryPk;
    private final Integer reward;

    public static DiarySaveResponse of(Long diaryPk, Integer reward){
        return DiarySaveResponse.builder()
                .diaryPk(diaryPk)
                .reward(reward)
                .build();
    }
}
