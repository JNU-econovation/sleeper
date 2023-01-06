package econo.app.sleeper.web.character;

import econo.app.sleeper.domain.Status;
import econo.app.sleeper.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class NewCharacterDto {

    private final User user;

    public static NewCharacterDto of(User user){
        return NewCharacterDto.builder()
                .user(user)
                .build();
    }

}
