package econo.app.sleeper.web.user;

import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.service.character.CharacterService;
import econo.app.sleeper.service.money.MoneyService;
import econo.app.sleeper.service.user.UserService;
import econo.app.sleeper.web.character.NewCharacterDto;
import econo.app.sleeper.web.common.CommonResponse;
import econo.app.sleeper.web.money.InitialMoneyDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CommonResponse> signup(@RequestBody SignUpRequest signUpRequest) {
        User user = userService.join(signUpRequest);
        characterService.createCharacter(NewCharacterDto.of(user));
        moneyService.createMoney(InitialMoneyDto.of(user));
        CommonResponse commonResponse = CommonResponse.of("회원가입 완료");
        return new ResponseEntity<>(commonResponse,HttpStatus.CREATED);
    }


}
