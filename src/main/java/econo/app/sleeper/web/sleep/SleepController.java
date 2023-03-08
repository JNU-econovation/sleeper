package econo.app.sleeper.web.sleep;
import econo.app.sleeper.service.character.CharacterService;
import econo.app.sleeper.service.sleep.SleepService;
import econo.app.sleeper.web.common.CommonResponse;
import econo.app.sleeper.web.sleep.dto.ActualRequest;
import econo.app.sleeper.web.sleep.dto.SleepDto;
import econo.app.sleeper.web.sleep.dto.SleepRequest;
import econo.app.sleeper.web.sleep.dto.SleepResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping ("/sleeps")
    public ResponseEntity<SleepResponse> saveSleep(@RequestBody @Valid SleepRequest sleepRequest){
        Long sleepPk = sleepService.saveSleep(sleepRequest);
        characterService.oppositeStatus(sleepRequest.getCharacterPk());
        SleepResponse sleepResponse = SleepResponse.of(sleepPk);
        return new ResponseEntity<>(sleepResponse,HttpStatus.OK);
    }

    @PutMapping("/sleeps/{nu}")
    public ResponseEntity<CommonResponse> updateActualTime(@PathVariable("nu") Long sleepPk,
                                                           @RequestBody @Valid ActualRequest actualRequest){
        SleepDto sleepDto = SleepDto.of(sleepPk, actualRequest.getActualWakeTime());
        Integer increasingExperience = sleepService.updateActualWakeTime(sleepDto);
        characterService.updateCharacterXp(actualRequest.getCharacterPk(),increasingExperience);
        characterService.oppositeStatus(actualRequest.getCharacterPk());
        CommonResponse commonResponse = CommonResponse.of("실제 기상 시간 업데이트 완료");
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

}
