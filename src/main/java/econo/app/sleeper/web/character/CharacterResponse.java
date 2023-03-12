package econo.app.sleeper.web.character;

import econo.app.sleeper.domain.character.Color;
import econo.app.sleeper.domain.character.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
@Builder
public class CharacterResponse {

    private final String color;
    private final String status;
    private final Integer cumulativeXp;
    private final Long level;

    public static CharacterResponse of(Color color, Status status, Integer cumulativeXp, Long level){
        return CharacterResponse.builder()
                .color(color.name())
                .status(status.name())
                .cumulativeXp(cumulativeXp)
                .level(level)
                .build();
    }
}
