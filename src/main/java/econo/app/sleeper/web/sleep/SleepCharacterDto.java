package econo.app.sleeper.web.sleep;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class SleepCharacterDto {

    private final String userId;
    private final Long sleepPk;

    public static SleepCharacterDto of(String userId, Long sleepPk){
        return SleepCharacterDto.builder()
                .userId(userId)
                .sleepPk(sleepPk)
                .build();
    }
}
