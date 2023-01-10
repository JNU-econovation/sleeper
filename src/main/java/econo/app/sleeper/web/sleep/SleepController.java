package econo.app.sleeper.web.sleep;
import econo.app.sleeper.domain.sleep.Sleep;
import econo.app.sleeper.service.character.CharacterService;
import econo.app.sleeper.service.sleep.SleepService;
import econo.app.sleeper.web.common.CommonRequest;
import econo.app.sleeper.web.common.CommonResponse;
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
    public ResponseEntity<SleepResponse> saveSetTime(CommonRequest commonRequest){
        Sleep sleep = sleepService.saveSetTime(commonRequest.getUserId());
        SleepResponse sleepResponse = SleepResponse.of("설정 수면 시간 저장 완료", sleep.getSleepPk());
        return new ResponseEntity<>(sleepResponse,HttpStatus.CREATED);
    }

    @PutMapping("/sleeps/{nu}/setTime")
    public ResponseEntity<CommonResponse> updateSetTime(@PathVariable("nu") Long sleepPk, SetTimeDto setTimeDto){
        sleepService.updateSetTime(sleepPk,setTimeDto);
        CommonResponse commonResponse = CommonResponse.of("설정 수면 시간 업데이트 완료", setTimeDto.getUserId());
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }


    @PutMapping("/sleeps/{nu}/actualTime")
    public ResponseEntity<CommonResponse> updateActualTime(@PathVariable("nu") Long sleepPk,
                                                           ActualRequest actualRequest){
        SleepDto sleepDto = SleepDto.of(sleepPk, actualRequest.getActualWakeTime());
        sleepService.updateActualWakeTime(sleepDto);
        SleepCharacterDto sleepCharacterDto = SleepCharacterDto.of(actualRequest.getUserId(), sleepPk);
        characterService.update(sleepCharacterDto);
        CommonResponse commonResponse = CommonResponse.of("실제 수면 시간 저장 완료", actualRequest.getUserId());
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

}
