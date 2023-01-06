package econo.app.sleeper.web.user;

import econo.app.sleeper.domain.User;
import econo.app.sleeper.service.character.CharacterService;
import econo.app.sleeper.service.money.MoneyService;
import econo.app.sleeper.service.user.UserService;
import econo.app.sleeper.util.TimeManager;
import econo.app.sleeper.util.CommonResponse;
import econo.app.sleeper.web.character.NewCharacterDto;
import econo.app.sleeper.web.login.LoginUser;
import econo.app.sleeper.web.money.InitialMoneyDto;
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
    public ResponseEntity<SignupResponse> signupUser(SignUpRequest signUpRequest) {
        User user = userService.join(signUpRequest);
        SignupResponse signupResponse = SignupResponse.builder()
                .userId(user.getUserId())
                .build();
        NewCharacterDto newCharacterDto = NewCharacterDto.of(user);
        characterService.init(newCharacterDto);
        InitialMoneyDto initialMoneyDto = InitialMoneyDto.of(user);
        moneyService.init(initialMoneyDto);
        return new ResponseEntity<>(signupResponse,HttpStatus.CREATED);
    }

    @PutMapping("/users/time")
    public ResponseEntity<CommonResponse> updateGoalTime(@SessionAttribute Object loginUser , GoalTimeRequest goalTimeRequest) {
        LoginUser loginUser1 = (LoginUser) loginUser;
        GoalTimeDto goalTimeDto = GoalTimeDto.of(goalTimeRequest.getGoalSleepTime(), goalTimeRequest.getGoalWakeTime(), loginUser1.getUserId());
        userService.updateGoalTime(goalTimeDto);
        CommonResponse commonResponse = CommonResponse.of("목표수면시간, 목표기상시간 저장", ((LoginUser) loginUser).getUserId());
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @GetMapping("/users/time")
    public ResponseEntity<GoalTimeResponse> readGoalTime(@SessionAttribute Object loginUser) {
        LoginUser loginUser1 = (LoginUser) loginUser;
        User user = userService.readGoalTime(loginUser1.getUserId());
        List<LocalTime> localTimes = TimeManager.suggestWakeTime(user.getGoalSleepTime());
        GoalTimeResponse goalTimeResponse = GoalTimeResponse.of(user.getGoalSleepTime(), user.getGoalWakeTime(), localTimes);
        return new ResponseEntity<>(goalTimeResponse,HttpStatus.OK);
    }

}
