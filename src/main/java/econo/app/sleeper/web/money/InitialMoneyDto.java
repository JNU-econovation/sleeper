package econo.app.sleeper.web.money;

import econo.app.sleeper.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class InitialMoneyDto {

    private final User user;

    public static InitialMoneyDto of(User user){
        return InitialMoneyDto.builder()
                .user(user)
                .build();
    }

}
