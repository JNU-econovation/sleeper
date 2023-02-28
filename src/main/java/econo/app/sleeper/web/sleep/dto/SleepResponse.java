package econo.app.sleeper.web.sleep.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class SleepResponse {

    private Long sleepPk;

    public static SleepResponse of(Long sleepPk){
        SleepResponse sleepResponse = SleepResponse.builder()
                .sleepPk(sleepPk)
                .build();
        return sleepResponse;
    }
}
