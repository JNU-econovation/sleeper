package econo.app.sleeper.web.character;

import econo.app.sleeper.domain.Character;
import econo.app.sleeper.domain.Color;
import econo.app.sleeper.domain.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
@Builder
public class CharacterResponse {

    private final Color color;
    private final Status status;
    private final Integer experience;
    private final Long level;
    private final String speechBubble;

    public static Character toEntity(CharacterResponse characterResponse){
        return Character.builder()
                .color(characterResponse.getColor())
                .status(characterResponse.getStatus())
                .experience(characterResponse.getExperience())
                .level(characterResponse.getLevel())
                .speechBubble(characterResponse.getSpeechBubble())
                .build();
    }
}
