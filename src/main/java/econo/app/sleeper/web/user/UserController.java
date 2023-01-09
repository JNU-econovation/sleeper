package econo.app.sleeper.web.user;

import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.service.character.CharacterService;
import econo.app.sleeper.service.money.MoneyService;
import econo.app.sleeper.service.user.UserService;
import econo.app.sleeper.util.DateTimeManager;
import econo.app.sleeper.web.CommonRequest;
import econo.app.sleeper.web.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "user", description = "사용자 관련 API")
public class UserController {
    private final UserService userService;
    private final CharacterService characterService;

    private final MoneyService moneyService;

    @Operation(summary = "api simple explain", description = "api specific explain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })

    @PostMapping("/users")
    public ResponseEntity<CommonResponse> signupUser(SignUpRequest signUpRequest) {
        User user = userService.join(signUpRequest);
        CommonResponse commonResponse = CommonResponse.of("회원가입 완료", user.getUserId());
        return new ResponseEntity<>(commonResponse,HttpStatus.CREATED);
    }

    @PutMapping("/users/time")
    public ResponseEntity<CommonResponse> updateGoalTime(GoalTimeRequest goalTimeRequest) {
        GoalTimeDto goalTimeDto = GoalTimeDto.of(goalTimeRequest.getGoalSleepTime(), goalTimeRequest.getGoalWakeTime(), goalTimeRequest.getUserId());
        userService.updateGoalTime(goalTimeDto);
        CommonResponse commonResponse = CommonResponse.of("목표수면시간, 목표기상시간 저장", goalTimeDto.getUserId());
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @GetMapping("/users/time")
    public ResponseEntity<GoalTimeResponse> readGoalTime(CommonRequest commonRequest) {
        User user = userService.readGoalTime(commonRequest.getUserId());
        List<LocalTime> localTimes = DateTimeManager.suggestWakeTime(user.getGoalSleepTime());
        GoalTimeResponse goalTimeResponse = GoalTimeResponse.of(user.getGoalSleepTime(), user.getGoalWakeTime(), localTimes);
        return new ResponseEntity<>(goalTimeResponse,HttpStatus.OK);
    }

}
