package econo.app.sleeper.web.user;

import econo.app.sleeper.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class SignupResponse {

    private final String userId;

    public static SignupResponse toDto(User user){
        return SignupResponse.builder()
                .userId(user.getUserId())
                .build();
    }


}
