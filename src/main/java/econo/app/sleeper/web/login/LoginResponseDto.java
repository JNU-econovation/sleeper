package econo.app.sleeper.web.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@Builder
public class LoginResponseDto implements Serializable{

    private String message;
    private String accessToken;
    private String refreshToken;


}
