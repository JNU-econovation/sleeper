package econo.app.sleeper.web.common;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class CommonResponse {

    private final String message;
    private final String userId;

    public static CommonResponse of(String message, String userId){
        return CommonResponse.builder()
                .message(message)
                .userId(userId)
                .build();
    }

    public static CommonResponse of(String message){
        return CommonResponse.builder()
                .message(message)
                .build();
    }

}
