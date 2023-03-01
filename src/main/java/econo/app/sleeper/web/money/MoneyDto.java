package econo.app.sleeper.web.money;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class MoneyDto {

    private final Long userPk;
    private final Long level;

    public static MoneyDto of(Long userPk, Long level){
        return MoneyDto.builder()
                .userPk(userPk)
                .level(level)
                .build();
    }
}
