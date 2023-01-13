package econo.app.sleeper.web.character;

import econo.app.sleeper.domain.character.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class CharacterDto {

    private final Long userPk;
    private final String content;

    public static CharacterDto of(Long userPk, String content){
        return CharacterDto.builder()
                .userPk(userPk)
                .content(content)
                .build();
    }
}
