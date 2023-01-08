package econo.app.sleeper.web.sleep;
import econo.app.sleeper.domain.Sleep;
import econo.app.sleeper.service.character.CharacterService;
import econo.app.sleeper.service.sleep.SleepService;
import econo.app.sleeper.web.CommonResponse;
import econo.app.sleeper.web.login.LoginUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "user", description = "사용자 관련 API")
public class SleepController {

    private final SleepService sleepService;
    private final CharacterService characterService;

    @PostMapping("/sleeps")
    public ResponseEntity<SleepResponse> saveSetTime(@SessionAttribute Object loginUser, SetTimeRequest setTimeRequest){
        LoginUser loginUser1 = (LoginUser) loginUser;
        SetTimeDto setTimeDto = SetTimeDto.of(setTimeRequest.getSetSleepTime(), setTimeRequest.getSetWakeTime(), loginUser1.getUserId());
        Sleep sleep = sleepService.saveSetTime(setTimeDto);
        SleepResponse sleepResponse = SleepResponse.of("수면 설정 시간 저장 완료", sleep.getSleepPk());
        return new ResponseEntity<>(sleepResponse,HttpStatus.CREATED);
    }

    @PutMapping("/sleeps/{nu}")
    public ResponseEntity<CommonResponse> updateActualTime(@SessionAttribute Object loginUser,@PathVariable("nu") Long sleepPk,
                                                           ActualRequest actualRequest){
        LoginUser loginUser1 = (LoginUser) loginUser;
        SleepDto sleepDto = SleepDto.of(sleepPk, actualRequest.getActualWakeTime());
        sleepService.updateActualWakeTime(sleepDto);
        SleepCharacterDto sleepCharacterDto = SleepCharacterDto.of(loginUser1.getUserId(), sleepPk);
        characterService.update(sleepCharacterDto);
        CommonResponse commonResponse = CommonResponse.of("실제 수면 시간 저장 완료", loginUser1.getUserId());
        return new ResponseEntity<>(commonResponse,HttpStatus.CREATED);
    }

}
