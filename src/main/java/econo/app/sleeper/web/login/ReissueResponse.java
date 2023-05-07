package econo.app.sleeper.web.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ReissueResponse {

    private String accessToken;
    private String refreshToken;
    private String reIssueToken;

    public static ReissueResponse of (String accessToken, String refreshToken, String reIssueToken){
        return ReissueResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .reIssueToken(reIssueToken)
                .build();
    }
}
