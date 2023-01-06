package econo.app.sleeper.web.diary;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class DiaryRewardDto {

    private final String content;
    private final String userId;
    private final Long diaryPk;

    public static DiaryRewardDto of(String content, String userId, Long diaryPk){
        return DiaryRewardDto.builder()
                .content(content)
                .userId(userId)
                .diaryPk(diaryPk)
                .build();
    }
}
