package econo.app.sleeper.web.user;

import econo.app.sleeper.service.user.SleepAdvisorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "sleepAdvisor", description = "사용자의 수면정보 관련 API")
public class SleepAdvisorController {

    private SleepAdvisorService sleepAdvisorService;


    @GetMapping("/sleepAdvisor/{id}")
    public ResponseEntity<RecommendedTimes> recommendWakeTimes(@PathVariable Long id, WakeTimeRecommendRequest wakeTimeRecommendRequest){
        WakeTimeRecommendDto wakeTimeRecommendDto = WakeTimeRecommendDto.of(id, wakeTimeRecommendRequest.getExpectedSleepTime());
        RecommendedTimes recommendedTimes = sleepAdvisorService.recommendWakeTimes(wakeTimeRecommendDto);
        return new ResponseEntity<>(recommendedTimes, HttpStatus.OK);
    }
}
