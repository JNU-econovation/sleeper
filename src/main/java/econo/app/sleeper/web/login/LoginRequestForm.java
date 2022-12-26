package econo.app.sleeper.web.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequestForm {

    // validation 해야됨
    private final String userId;

    private final String userPassword;


}
