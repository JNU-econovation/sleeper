package econo.app.sleeper.web.sleep;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class SleepCharacterDto {

    private final Long userPk;
    private final Long sleepPk;

    public static SleepCharacterDto of(Long userPk, Long sleepPk){
        return SleepCharacterDto.builder()
                .userPk(userPk)
                .sleepPk(sleepPk)
                .build();
    }
}
