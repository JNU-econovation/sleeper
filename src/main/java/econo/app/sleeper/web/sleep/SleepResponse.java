package econo.app.sleeper.web.sleep;

import econo.app.sleeper.repository.SleepRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class SleepResponse {

    private final String message;

    public SleepResponse toDto(String message){
        return SleepResponse.builder()
                .message(message)
                .build();
    }

}
