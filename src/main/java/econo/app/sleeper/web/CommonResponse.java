package econo.app.sleeper.web;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class CommonResponse {

    private final String message;

    public static CommonResponse toDto(String message){
        return CommonResponse.builder()
                .message(message)
                .build();
    }

}
