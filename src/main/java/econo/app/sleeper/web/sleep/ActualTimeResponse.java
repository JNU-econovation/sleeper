package econo.app.sleeper.web.sleep;


import econo.app.sleeper.web.common.CommonResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class ActualTimeResponse {

    private final String message;
    private final Long sleepPk;
    private final Long userPk;

    public static ActualTimeResponse of(String message, Long sleepPk, Long userPk){
        return ActualTimeResponse.builder()
                .message(message)
                .sleepPk(sleepPk)
                .userPk(userPk)
                .build();
    }
}
