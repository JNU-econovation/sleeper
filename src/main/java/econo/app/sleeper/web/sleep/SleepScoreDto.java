package econo.app.sleeper.web.sleep;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
public class SleepScoreDto {

    private final List<Long> sleepPks;

    public static SleepScoreDto of(List<Long> sleepPks){
        return SleepScoreDto.builder()
                .sleepPks(sleepPks)
                .build();
    }

}
