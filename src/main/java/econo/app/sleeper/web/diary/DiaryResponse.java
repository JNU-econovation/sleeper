package econo.app.sleeper.web.diary;

import econo.app.sleeper.domain.Diary;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class DiaryResponse {


    private final Long diaryPk;
    private final Integer increasedMoney;

    public static DiaryResponse of(Long diaryPk,Integer increasedMoney){
        return DiaryResponse.builder()
                .diaryPk(diaryPk)
                .increasedMoney(increasedMoney)
                .build();
    }
}
