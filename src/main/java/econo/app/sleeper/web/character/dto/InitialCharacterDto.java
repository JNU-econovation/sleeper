package econo.app.sleeper.web.character.dto;

import econo.app.sleeper.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class InitialCharacterDto {

    private final User user;

    public static InitialCharacterDto of(User user){
        return InitialCharacterDto.builder()
                .user(user)
                .build();
    }

}
