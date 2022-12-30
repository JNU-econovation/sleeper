package econo.app.sleeper.web.sleep;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class SleepDto {

    private final String userId;
    private final Long diaryPk;
    private final ActualRequestParam actualRequestParam;

    public static SleepDto of(String userId, Long diaryPk, ActualRequestParam actualRequestParam){
        return SleepDto.builder()
                .userId(userId)
                .diaryPk(diaryPk)
                .actualRequestParam(actualRequestParam)
                .build();
    }
}
