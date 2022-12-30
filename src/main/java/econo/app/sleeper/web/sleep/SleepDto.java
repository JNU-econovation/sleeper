package econo.app.sleeper.web.sleep;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class SleepDto {

    private final String userId;
    private final Long sleepPk;
    private final ActualRequestParam actualRequestParam;

    public static SleepDto of(String userId, Long sleepPk, ActualRequestParam actualRequestParam){
        return SleepDto.builder()
                .userId(userId)
                .sleepPk(sleepPk)
                .actualRequestParam(actualRequestParam)
                .build();
    }
}
