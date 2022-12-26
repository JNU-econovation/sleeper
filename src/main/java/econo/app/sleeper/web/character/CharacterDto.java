package econo.app.sleeper.web.character;

import econo.app.sleeper.domain.Character;
import econo.app.sleeper.domain.Color;
import econo.app.sleeper.domain.Status;
import econo.app.sleeper.repository.CharacterRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
@Builder
public class CharacterDto {

    private final Color color;
    private final Status status;
    private final Integer experience;
    private final Long level;
    private final String speechBubble;

    public static Character toEntity(CharacterDto characterDto){
        return Character.builder()
                .color(characterDto.getColor())
                .status(characterDto.getStatus())
                .experience(characterDto.getExperience())
                .level(characterDto.getLevel())
                .speechBubble(characterDto.getSpeechBubble())
                .build();
    }
}
