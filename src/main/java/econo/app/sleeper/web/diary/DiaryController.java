package econo.app.sleeper.web.diary;

import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.service.character.CharacterService;
import econo.app.sleeper.service.diary.DiaryService;
import econo.app.sleeper.service.money.MoneyService;
import econo.app.sleeper.web.CommonResponse;
import econo.app.sleeper.web.login.LoginUser;
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

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "diary", description = "감사일기 관련 API")
public class DiaryController {

    private final DiaryService diaryService;
    private final MoneyService moneyService;
    private final CharacterService characterService;

    @Operation(summary = "api simple explain", description = "api specific explain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })

    @PostMapping("/diaries")
    public ResponseEntity<CommonResponse> saveDiary(DiaryRequest diaryRequest) {
        diaryService.save(diaryRequest);
        CommonResponse commonResponse = CommonResponse.of("감사일기 저장 완료", diaryRequest.getUserId());
        return new ResponseEntity<>(commonResponse,HttpStatus.CREATED);
    }

    @PutMapping("/diaries/{nu}")
    public ResponseEntity<DiaryResponse> updateDiary(@PathVariable("nu") Long diaryPk,DiaryRequest diaryRequest){
        diaryService.updateDiary(diaryPk,diaryRequest.getContent());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/diaries/{nu}")
    public ResponseEntity<DiaryResponse> deleteDiary(@PathVariable("nu") Long diaryPk){
        diaryService.deleteDiary(diaryPk);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/diaries/{nu}")
    public ResponseEntity<DiaryResponse> findDiary(@PathVariable("nu") Long diaryPk){
        diaryService.findDiary(diaryPk);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/diaries")
    public ResponseEntity<DiaryFindResponse> findDiariesByUser(@SessionAttribute Object loginUser) {
        LoginUser loginUser1 = (LoginUser) loginUser;
        List<Diary> diariesByUser = diaryService.findDiariesByUser(loginUser1.getUserId());
        DiaryFindResponse diaryFindResponseList = null;
        for (int i = 0; i < diariesByUser.size(); i++) {
            diaryFindResponseList = DiaryFindResponse.of(diariesByUser.get(i).getContent().getContent(), diariesByUser.get(i).getSavingDate());
        }
        return new ResponseEntity<>(diaryFindResponseList, HttpStatus.OK);
    }

    @GetMapping("/diaries/date/{date}")
    public ResponseEntity<DiaryFindResponse> findDiariesByDate(
            @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date") LocalDate date,
            @SessionAttribute Object loginUser) {
        LoginUser loginUser1 = (LoginUser) loginUser;
        DiaryFindDto diaryFindDto = DiaryFindDto.of(loginUser1.getUserId(), date);
        List<Diary> diariesByDate = diaryService.findDiariesByDate(diaryFindDto);

        DiaryFindResponse diaryFindResponseList = null;
        for (int i = 0; i < diariesByDate.size(); i++) {
            diaryFindResponseList = DiaryFindResponse.of(diariesByDate.get(i).getContent().getContent(), diariesByDate.get(i).getSavingDate());
        }
        return new ResponseEntity<>(diaryFindResponseList, HttpStatus.CREATED);
    }


}
