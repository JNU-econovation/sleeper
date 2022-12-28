package econo.app.sleeper.web.user;

import econo.app.sleeper.domain.User;
import econo.app.sleeper.service.user.UserService;
import econo.app.sleeper.web.login.LoginUser;
import econo.app.sleeper.web.login.SessionConst;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequiredArgsConstructor
@Tag(name = "user", description = "사용자 관련 API")
public class UserController {
    private final UserService userService;

    @Operation(summary = "api simple explain", description = "api specific explain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })

    @PostMapping("/users")
    public ResponseEntity<SignupResponse> signupUser(SignUpRequestForm signUpRequestForm) {
        User user = userService.join(signUpRequestForm);
        SignupResponse signupResponse = SignupResponse.builder()
                .userId(user.getUserId())
                .build();
        return new ResponseEntity<>(signupResponse,HttpStatus.CREATED);
    }

    @PutMapping("/users/time")
    public ResponseEntity<SignupResponse> updateGoalTime(@SessionAttribute(SessionConst.LOGIN_USER) Object loginUser , GoalTimeRequestForm GoaltimeRequestForm) {
        LoginUser loginUser1 = (LoginUser) loginUser;
        GoalTimeDto goalTimeDto = GoalTimeDto.builder()
                .goalWakeTime(GoaltimeRequestForm.getGoalWakeTime())
                .goalSleepTime(GoaltimeRequestForm.getGoalSleepTime())
                .userId(loginUser1.getUserId())
                .build();
        userService.updateGoalTime(goalTimeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
