package econo.app.sleeper.web.sleep.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
public class RecommendedTimes {

    private final List<LocalTime> recommendedTimes;

    public static RecommendedTimes of(List<LocalTime> recommendedTimes){
        return RecommendedTimes.builder()
                .recommendedTimes(recommendedTimes)
                .build();
    }

}
