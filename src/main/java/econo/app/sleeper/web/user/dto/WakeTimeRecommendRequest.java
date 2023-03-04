package econo.app.sleeper.web.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
@Builder
public class WakeTimeRecommendRequest {

    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime expectedSleepTime;

}
