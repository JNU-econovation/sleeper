package econo.app.sleeper.web.sleep;
import econo.app.sleeper.domain.sleep.Sleep;
import econo.app.sleeper.service.speechBubble.SpeechBubbleService;
import econo.app.sleeper.service.character.CharacterService;
import econo.app.sleeper.service.sleep.SleepService;
import econo.app.sleeper.util.DateTimeManager;
import econo.app.sleeper.web.character.CharacterDto;
import econo.app.sleeper.web.common.CommonRequest;
import econo.app.sleeper.web.common.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "sleep", description = "수면 관련 API")
public class SleepController {

    private final SleepService sleepService;
    private final CharacterService characterService;

    private final SpeechBubbleService speechBubbleService;

    @Operation(summary = "api simple explain", description = "api specific explain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })

    @GetMapping("/sleeps/{nu}/setTime")
    public ResponseEntity<SetTimeResponse> readSetTime(@PathVariable("nu") Long sleepPk){
        SetTimeResponse setTimeResponse = sleepService.readSetTime(sleepPk);
        return new ResponseEntity<>(setTimeResponse,HttpStatus.OK);
    }

    @GetMapping("/sleeps/recommend")
    public ResponseEntity<SleepResponse> saveSetTime(@RequestBody @Valid CommonRequest commonRequest){
        Sleep sleep = sleepService.saveSetTime(commonRequest.getUserPk());
        SleepResponse sleepResponse = SleepResponse.of("설정 수면 시간 저장 완료", sleep.getId());
        return new ResponseEntity<>(sleepResponse,HttpStatus.CREATED);
    }

    @GetMapping("/sleeps/{nu}/setTime")
    public ResponseEntity<SetTimeResponse> readSetTime(@PathVariable("nu") Long sleepPk){
        SetTimeResponse setTimeResponse = sleepService.readSetTime(sleepPk);
        return new ResponseEntity<>(setTimeResponse,HttpStatus.OK);
    }

    @GetMapping("/sleeps/recommend")
    public ResponseEntity<RecommendedTimes> recommendWakeTimes(@Valid SetSleepTimeDto setSleepTimeDto){
        LocalTime setSleepTime = setSleepTimeDto.getSetSleepTime();
        List<LocalTime> localTimess = DateTimeManager.suggestWakeTime(setSleepTime);
        RecommendedTimes recommendedTimes = RecommendedTimes.of(localTimess);
        return new ResponseEntity<>(recommendedTimes,HttpStatus.OK);
    }

    @PutMapping("/sleeps/{nu}/setTime")
    public ResponseEntity<CommonResponse> updateSetTime(@PathVariable("nu") Long sleepPk, @RequestBody @Valid SetTimeRequest setTimeRequest){
        sleepService.updateSetTime(sleepPk, setTimeRequest);
        speechBubbleService.afterSettingSetTime();
        CommonResponse commonResponse = CommonResponse.of("설정 수면 시간 업데이트 완료", setTimeRequest.getUserPk());
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }


    @PutMapping("/sleeps/{nu}/actualTime")
    public ResponseEntity<CommonResponse> updateActualTime(@PathVariable("nu") Long sleepPk,
                                                           @RequestBody @Valid ActualRequest actualRequest){
        SleepDto sleepDto = SleepDto.of(sleepPk, actualRequest.getActualWakeTime());
        sleepService.updateActualWakeTime(sleepDto);
        Integer plusExperience = sleepService.assessExperience(sleepPk);
        CharacterDto characterDto = CharacterDto.of(actualRequest.getUserPk(), plusExperience);
        Long characterPk = characterService.updateGrowthAndStatus(characterDto);
        Boolean approachLevel = characterService.approachLevel(characterPk);
        speechBubbleService.judgeSpeechBubbleAfterWakeUp(approachLevel);
        CommonResponse commonResponse = CommonResponse.of("실제 수면 시간 저장 완료", actualRequest.getUserPk());
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

}
