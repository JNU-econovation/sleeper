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
    public ResponseEntity<SleepResponse> saveSetTime(TimeRequestDto timeRequestDto){
        Sleep sleep = sleepService.saveSetTime(timeRequestDto);
        SleepResponse sleepResponse = SleepResponse.builder()
                .message("수면 설정 시간 저장 완료")
                .sleepPk(sleep.getSleepPk())
                .build();
        return new ResponseEntity<>(sleepResponse,HttpStatus.CREATED);
    }

    @PutMapping("/sleeps/{nu}")
    public ResponseEntity<CommonResponse> updateActualTime(@SessionAttribute Object loginUser,@PathVariable("nu") Long sleepPk,
                                                           ActualRequestParam actualRequestParam){
        LoginUser loginUser1 = (LoginUser) loginUser;
        SleepDto sleepDto = SleepDto.of(loginUser1.getUserId(),sleepPk,actualRequestParam);
        sleepService.updateActualTime(sleepDto);
        SleepCharacterDto sleepCharacterDto = SleepCharacterDto.of(loginUser1.getUserId(), sleepPk);
        characterService.updateCharacter(sleepCharacterDto);
        CommonResponse commonResponse = CommonResponse.toDto("실제 수면 시간 저장 완료");
        return new ResponseEntity<>(commonResponse,HttpStatus.CREATED);
    }

}
