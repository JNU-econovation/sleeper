package econo.app.sleeper.web.sleep;

import econo.app.sleeper.service.sleep.SleepAdvisorService;
import econo.app.sleeper.web.sleep.dto.RecommendedTimes;
import econo.app.sleeper.web.sleep.dto.SleepAdvisorDto;
import econo.app.sleeper.web.sleep.dto.SleepAdvisorRequest;
import econo.app.sleeper.web.sleep.dto.SleepAdvisorResponse;
import econo.app.sleeper.web.member.dto.WakeTimeRecommendDto;
import econo.app.sleeper.web.member.dto.WakeTimeRecommendRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "sleepAdvisor", description = "사용자의 수면정보 관련 API")
public class SleepAdvisorController {
    private SleepAdvisorService sleepAdvisorService;

    @Operation(summary = "api simple explain", description = "api specific explain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })

    @GetMapping("/sleepAdvisor/{nu}")
    public ResponseEntity<RecommendedTimes> recommendWakeTimes(@PathVariable("nu") Long sleepAdvisorPk, WakeTimeRecommendRequest wakeTimeRecommendRequest){
        WakeTimeRecommendDto wakeTimeRecommendDto = WakeTimeRecommendDto.of(sleepAdvisorPk, wakeTimeRecommendRequest.getExpectedSleepTime());
        RecommendedTimes recommendedTimes = sleepAdvisorService.recommendWakeTimes(wakeTimeRecommendDto);
        return new ResponseEntity<>(recommendedTimes, HttpStatus.OK);
    }
    @PutMapping("/sleepAdvisor/{nu}")
    public ResponseEntity<SleepAdvisorResponse> updateSleepAdvisor(@PathVariable("nu") Long sleepAdvisorPk, SleepAdvisorRequest sleepAdvisorRequest){
        SleepAdvisorDto sleepAdvisorDto = SleepAdvisorDto.of(sleepAdvisorPk, sleepAdvisorRequest.getGoalSleepTime(), sleepAdvisorRequest.getGoalWakeTime(), sleepAdvisorRequest.getMinimumSleepTime());
        SleepAdvisorResponse sleepAdvisorResponse = sleepAdvisorService.updateSleepAdvisor(sleepAdvisorDto);
        return new ResponseEntity<>(sleepAdvisorResponse,HttpStatus.OK);
    }
}
