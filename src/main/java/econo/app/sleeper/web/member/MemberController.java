package econo.app.sleeper.web.member;

import econo.app.sleeper.domain.member.Member;
import econo.app.sleeper.service.character.CharacterService;
import econo.app.sleeper.service.member.MemberService;
import econo.app.sleeper.service.money.MoneyService;
import econo.app.sleeper.service.sleep.SleepAdvisorService;
import econo.app.sleeper.web.character.dto.InitialCharacterDto;
import econo.app.sleeper.web.common.CommonResponse;
import econo.app.sleeper.web.money.InitialMoneyDto;
import econo.app.sleeper.web.sleep.dto.InitialSleepAdvisorDto;
import econo.app.sleeper.web.member.dto.IdRequest;
import econo.app.sleeper.web.member.dto.SignUpRequest;
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
public class MemberController {

    private final MemberService memberService;
    private final SleepAdvisorService sleepAdvisorService;
    private final CharacterService characterService;
    private final MoneyService moneyService;

    @Operation(summary = "api simple explain", description = "api specific explain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })


    @PostMapping("/users/idCheck")
    public ResponseEntity<Boolean> idCheck(@RequestBody IdRequest idRequest){
        String check= memberService.idCheck(idRequest.getRequestId());
       if(check.equals("중복"))
       { return new ResponseEntity<>(false,HttpStatus.OK);}
       return new ResponseEntity<>(true,HttpStatus.FORBIDDEN);
    }

    @PostMapping("/users")
    public ResponseEntity<CommonResponse> signup(@RequestBody SignUpRequest signUpRequest) {
        Member member = memberService.join(signUpRequest);
        sleepAdvisorService.create(InitialSleepAdvisorDto.of(member,signUpRequest.getGoalSleepTime(),signUpRequest.getGoalWakeTime(),signUpRequest.getMinimumSleepTime()));
        characterService.createCharacter(InitialCharacterDto.of(member));
        moneyService.createMoney(InitialMoneyDto.of(member));
        CommonResponse commonResponse = CommonResponse.of("회원가입 완료");
        return new ResponseEntity<>(commonResponse,HttpStatus.CREATED);
    }


}
