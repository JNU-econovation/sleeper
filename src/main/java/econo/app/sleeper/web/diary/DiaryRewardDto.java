package econo.app.sleeper.web.diary;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class DiaryRewardDto {

    private final String content;
    private final Long userPk;

    public static DiaryRewardDto of(String content, Long userPk){
        return DiaryRewardDto.builder()
                .content(content)
                .userPk(userPk)
                .build();
    }
}
