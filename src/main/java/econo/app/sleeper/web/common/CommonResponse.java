package econo.app.sleeper.web.common;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class CommonResponse {

    private final String message;
    private final Long userPk;

    public static CommonResponse of(String message, Long userPk){
        return CommonResponse.builder()
                .message(message)
                .userPk(userPk)
                .build();
    }

    public static CommonResponse of(String message){
        return CommonResponse.builder()
                .message(message)
                .build();
    }

}
