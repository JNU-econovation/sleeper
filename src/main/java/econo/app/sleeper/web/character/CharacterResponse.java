package econo.app.sleeper.web.character;

import econo.app.sleeper.domain.character.Color;
import econo.app.sleeper.domain.character.Status;
import econo.app.sleeper.domain.character.Growth;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
@Builder
public class CharacterResponse {

    private final Color color;
    private final Status status;
    private final Growth growth;
    private final String  speechBubble;

    public CharacterResponse of(Color color, Status status, Growth growth, String speechBubble){
        return CharacterResponse.builder()
                .color(color)
                .status(status)
                .growth(growth)
                .speechBubble(speechBubble)
                .build();
    }
}
