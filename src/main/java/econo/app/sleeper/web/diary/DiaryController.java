package econo.app.sleeper.web.diary;
import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.service.speechBubble.SpeechBubbleService;
import econo.app.sleeper.service.character.CharacterService;
import econo.app.sleeper.service.diary.DiaryService;
import econo.app.sleeper.service.money.MoneyService;
import econo.app.sleeper.service.sleep.SleepService;
import econo.app.sleeper.web.common.CommonRequest;
import econo.app.sleeper.web.common.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "diary", description = "감사일기 관련 API")
public class DiaryController {

    private final DiaryService diaryService;
    private final SleepService sleepService;
    private final MoneyService moneyService;
    private final CharacterService characterService;

    private final SpeechBubbleService speechBubbleService;

    @Operation(summary = "api simple explain", description = "api specific explain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })

    @PostMapping("/diaries")
    public ResponseEntity<DiarySaveResponse> saveDiary(@RequestBody @Valid DiaryRequest diaryRequest) {
        Long diaryPk = diaryService.save(diaryRequest);
        sleepService.updateActualSleepTime(diaryRequest.getUserPk());
        Integer reward = moneyService.obtainMoney(DiaryRewardDto.of(diaryRequest.getContent(), diaryRequest.getUserPk()));
        characterService.updateStatusToSleep(diaryRequest.getUserPk());
        speechBubbleService.judgeSpeechBubbleAfterSaveDiary(diaryRequest.getContent().length());
        DiarySaveResponse diarySaveResponse = DiarySaveResponse.of(diaryPk,reward);
        return new ResponseEntity<>(diarySaveResponse,HttpStatus.CREATED);
    }


    @PutMapping("/diaries/{nu}/continue")
    public ResponseEntity<DiarySaveResponse> continueDiary(@PathVariable("nu") Long diaryPk, @RequestBody DiaryContinueRequest diaryContinueRequest) {
        diaryService.updateDiary(diaryPk, diaryContinueRequest.getContent());
        sleepService.updateActualSleepTime(diaryContinueRequest.getUserPk());
        Integer reward = moneyService.obtainMoney(DiaryRewardDto.of(diaryContinueRequest.getContent(), diaryContinueRequest.getUserPk()));
        characterService.updateStatusToSleep(diaryContinueRequest.getUserPk());
        speechBubbleService.judgeSpeechBubbleAfterSaveDiary(diaryContinueRequest.getContent().length());
        DiarySaveResponse diarySaveResponse = DiarySaveResponse.of(diaryPk,reward);
        return new ResponseEntity<>(diarySaveResponse,HttpStatus.CREATED);
    }

    @PutMapping("/diaries/{nu}")
    public ResponseEntity<CommonResponse> editDiary(@PathVariable("nu") Long diaryPk, @RequestBody DiaryEditRequest diaryEditRequest){
        diaryService.updateDiary(diaryPk, diaryEditRequest.getContent());
        CommonResponse commonResponse = CommonResponse.of("감사일기 수정 완료");
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }


    @GetMapping("/diaries/check")
    public ResponseEntity<DiaryCheckDto> checkDiary(@Valid CommonRequest commonRequest){
        DiaryCheckDto diaryCheckDto = diaryService.giveDiaryIfPresent(commonRequest.getUserPk());
        return new ResponseEntity<>(diaryCheckDto,HttpStatus.OK);
    }


    @DeleteMapping("/diaries/{nu}")
    public ResponseEntity<CommonResponse> deleteDiary(@PathVariable("nu") Long diaryPk){
        diaryService.deleteDiary(diaryPk);
        CommonResponse commonResponse = CommonResponse.of("감사일기 삭제 완료");
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @GetMapping("/diaries/{nu}")
    public ResponseEntity<DiaryResponse> findDiary(@PathVariable("nu") Long diaryPk){
        Diary diary = diaryService.findDiary(diaryPk);
        DiaryResponse diaryResponse = DiaryResponse.of(diary.getContent().getContent(), diary.getSavingDate().getSavingDate(), diary.getSavingDate().getSavingDateTime());
        return new ResponseEntity<>(diaryResponse,HttpStatus.OK);
    }

    @GetMapping("/diaries")
    public ResponseEntity<DiaryFindResponse> findDiariesByUser(@Valid CommonRequest commonRequest) {
        List<Diary> diariesByUser = diaryService.findDiariesByUser(commonRequest.getUserPk());
        DiaryFindResponse diaryFindResponseList = null;
        for (int i = 0; i < diariesByUser.size(); i++) {
            diaryFindResponseList = DiaryFindResponse.of(diariesByUser.get(i).getContent().getContent(), diariesByUser.get(i).getSavingDate().getSavingDate());
        }
        return new ResponseEntity<>(diaryFindResponseList, HttpStatus.OK);
    }

    @GetMapping("/diaries/date/{date}")
    public ResponseEntity<DiaryFindResponse> findDiaryByDate(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date") LocalDate date,
                                                             @Valid CommonRequest commonRequest) {
        DiaryFindDto diaryFindDto = DiaryFindDto.of(commonRequest.getUserPk(), date);
        Diary diaryByDate = diaryService.findDiaryByDate(diaryFindDto);
        DiaryFindResponse diaryFindResponse = DiaryFindResponse.of(diaryByDate.getContent().getContent(), diaryByDate.getSavingDate().getSavingDate());
        return new ResponseEntity<>(diaryFindResponse, HttpStatus.CREATED);
    }

}
