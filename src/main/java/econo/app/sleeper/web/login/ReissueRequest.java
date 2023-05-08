package econo.app.sleeper.web.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ReissueRequest {

    private String accessToken;
    private String refreshToken;
    private String memberId;

    public static ReissueRequest of (String accessToken, String refreshToken, String memberId){
        return ReissueRequest.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .memberId(memberId)
                .build();
    }
}
