package econo.app.sleeper.web.character;

import econo.app.sleeper.domain.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class CharacterDto {

    private final String userId;
    private final String content;
    private final Status status;

    public static CharacterDto of(String userId, String content, Status status){
        return CharacterDto.builder()
                .userId(userId)
                .status(status)
                .content(content)
                .build();
    }
}
