package econo.app.sleeper.web.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class LoginRequest {

    private String userId;

    private String userPassword;

}

