package econo.app.sleeper.web.common;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class CommonResponse {

    private final String message;

    public static CommonResponse of(String message){
        return CommonResponse.builder()
                .message(message)
                .build();
    }



}
