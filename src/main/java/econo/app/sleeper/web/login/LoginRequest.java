package econo.app.sleeper.web.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequest {

    // validation 해야됨
    private String userId;

    private String userPassword;

}
