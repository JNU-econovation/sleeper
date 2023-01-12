package econo.app.sleeper.web.sleep;
import econo.app.sleeper.domain.sleep.Sleep;
import econo.app.sleeper.service.character.CharacterService;
import econo.app.sleeper.service.sleep.SleepService;
import econo.app.sleeper.util.DateTimeManager;
import econo.app.sleeper.web.common.CommonRequest;
import econo.app.sleeper.web.common.CommonResponse;
import econo.app.sleeper.web.user.GoalTimeResponse;
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
@Tag(name = "sleep", description = "수면 관련 API")
public class SleepController {

    private final SleepService sleepService;
    private final CharacterService characterService;

    @Operation(summary = "api simple explain", description = "api specific explain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })

    @PostMapping("/sleeps")
    public ResponseEntity<SleepResponse> saveSetTime(@RequestBody CommonRequest commonRequest){
        Sleep sleep = sleepService.saveSetTime(commonRequest.getUserId());
        SleepResponse sleepResponse = SleepResponse.of("설정 수면 시간 저장 완료", sleep.getSleepPk());
        return new ResponseEntity<>(sleepResponse,HttpStatus.CREATED);
    }

    @GetMapping("/sleeps/{nu}")// 추후 수면시간 추천에대한 로직이 바뀔 수도 있기 때문에 sleepPk를 넣어줌
    public ResponseEntity<RecommendedTimes> recommendWakeTimes(@PathVariable("nu") Long sleepPk, SetSleepTimeDto setSleepTimeDto){
        LocalTime setSleepTime = setSleepTimeDto.getSetSleepTime();
        System.out.println("setSleepTime = " + setSleepTime);
        List<LocalTime> localTimess = DateTimeManager.suggestWakeTime(setSleepTime);
        RecommendedTimes recommendedTimes = RecommendedTimes.of(localTimess);
        return new ResponseEntity<>(recommendedTimes,HttpStatus.OK);
    }

    @PutMapping("/sleeps/{nu}/setTime")
    public ResponseEntity<CommonResponse> updateSetTime(@PathVariable("nu") Long sleepPk, @RequestBody SetTimeDto setTimeDto){
        sleepService.updateSetTime(sleepPk,setTimeDto);
        CommonResponse commonResponse = CommonResponse.of("설정 수면 시간 업데이트 완료", setTimeDto.getUserId());
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }


    @PutMapping("/sleeps/{nu}/actualTime")
    public ResponseEntity<CommonResponse> updateActualTime(@PathVariable("nu") Long sleepPk,
                                                           @RequestBody ActualRequest actualRequest){
        SleepDto sleepDto = SleepDto.of(sleepPk, actualRequest.getActualWakeTime());
        sleepService.updateActualWakeTime(sleepDto);
        SleepCharacterDto sleepCharacterDto = SleepCharacterDto.of(actualRequest.getUserId(), sleepPk);
        characterService.update(sleepCharacterDto);
        CommonResponse commonResponse = CommonResponse.of("실제 수면 시간 저장 완료", actualRequest.getUserId());
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

}
