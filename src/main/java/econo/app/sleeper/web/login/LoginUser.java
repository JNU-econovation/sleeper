package econo.app.sleeper.web.login;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class LoginUser implements Serializable {

    private final String userId;

}
