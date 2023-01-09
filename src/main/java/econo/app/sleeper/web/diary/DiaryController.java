package econo.app.sleeper.web.diary;

import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.service.character.CharacterService;
import econo.app.sleeper.service.diary.DiaryService;
import econo.app.sleeper.service.money.MoneyService;
import econo.app.sleeper.web.CommonRequest;
import econo.app.sleeper.web.CommonResponse;
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

    @Operation(summary = "api simple explain", description = "api specific explain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })

    @PostMapping("/diaries")
    public ResponseEntity<DiaryResponse> saveDiary(DiaryRequest diaryRequest) {
        Diary diary = diaryService.save(diaryRequest);
        DiaryResponse diaryResponse = DiaryResponse.of(diary.getDiaryPk());
        return new ResponseEntity<>(diaryResponse,HttpStatus.CREATED);
    }

    @PutMapping("/diaries/{nu}")
    public ResponseEntity<CommonResponse> updateDiary(@PathVariable("nu") Long diaryPk,DiaryRequest diaryRequest){
        diaryService.updateDiary(diaryPk,diaryRequest.getContent());
        CommonResponse commonResponse = CommonResponse.of("감사일기 수정 완료");
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
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
    public ResponseEntity<DiaryFindResponse> findDiariesByUser(CommonRequest commonRequest) {
        List<Diary> diariesByUser = diaryService.findDiariesByUser(commonRequest.getUserId());
        DiaryFindResponse diaryFindResponseList = null;
        for (int i = 0; i < diariesByUser.size(); i++) {
            diaryFindResponseList = DiaryFindResponse.of(diariesByUser.get(i).getContent().getContent(), diariesByUser.get(i).getSavingDate().getSavingDate());
        }
        return new ResponseEntity<>(diaryFindResponseList, HttpStatus.OK);
    }

    @GetMapping("/diaries/date/{date}")
    public ResponseEntity<DiaryFindResponse> findDiariesByDate(
            @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date") LocalDate date,
           CommonRequest commonRequest) {
        DiaryFindDto diaryFindDto = DiaryFindDto.of(commonRequest.getUserId(), date);
        List<Diary> diariesByDate = diaryService.findDiariesByDate(diaryFindDto);

        DiaryFindResponse diaryFindResponseList = null;
        for (int i = 0; i < diariesByDate.size(); i++) {
            diaryFindResponseList = DiaryFindResponse.of(diariesByDate.get(i).getContent().getContent(), diariesByDate.get(i).getSavingDate().getSavingDate());
        }
        return new ResponseEntity<>(diaryFindResponseList, HttpStatus.CREATED);
    }

}
