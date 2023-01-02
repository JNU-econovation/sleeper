package econo.app.sleeper.web.diary;

import econo.app.sleeper.domain.Diary;
import econo.app.sleeper.service.diary.DiaryService;
import econo.app.sleeper.web.login.LoginUser;
import econo.app.sleeper.web.login.SessionConst;
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
    public ResponseEntity<DiaryResponse> saveDiary(DiaryRequestForm diaryRequestForm, @SessionAttribute(SessionConst.LOGIN_USER) Object loginUser) {
        LoginUser loginUser1 = (LoginUser) loginUser;
        DiaryTimeDto diaryTimeDto = DiaryTimeDto.of(loginUser1.getUserId(), diaryRequestForm.getContent(), diaryRequestForm.getLocalDateTime());
        DiaryResponse diaryResponse = diaryService.saveDiary(diaryTimeDto);
        return new ResponseEntity<>(diaryResponse,HttpStatus.CREATED);
    }

    @PutMapping("/diaries/{nu}")
    public ResponseEntity<DiaryResponse> updateDiary(@PathVariable("nu") Long diaryPk,DiaryParam diaryParam){
        diaryService.updateDiary(diaryPk,diaryParam.getContent());
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
    public ResponseEntity<DiaryResponseForm> findDiariesByUser(@SessionAttribute(SessionConst.LOGIN_USER) Object loginUser) {
        LoginUser loginUser1 = (LoginUser) loginUser;
        List<Diary> diariesByUser = diaryService.findDiariesByUser(loginUser1.getUserId());

        DiaryResponseForm diaryResponseFormList = null;
        for (int i = 0; i < diariesByUser.size(); i++) {
            diaryResponseFormList = DiaryResponseForm.of(diariesByUser.get(i).getContent(), diariesByUser.get(i).getSavingDate());
        }

        return new ResponseEntity<>(diaryResponseFormList, HttpStatus.OK);
    }

    @GetMapping("/diaries/date/{date}")
    public ResponseEntity<DiaryResponseForm> findDiariesByDate(
            @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date") LocalDate date,
            @SessionAttribute(SessionConst.LOGIN_USER) Object loginUser) {
        LoginUser loginUser1 = (LoginUser) loginUser;
        DiaryDateDto diaryDateDto = DiaryDateDto.of(loginUser1.getUserId(), date);
        List<Diary> diariesByDate = diaryService.findDiariesByDate(diaryDateDto);

        DiaryResponseForm diaryResponseFormList = null;
        for (int i = 0; i < diariesByDate.size(); i++) {
            diaryResponseFormList = DiaryResponseForm.of(diariesByDate.get(i).getContent(), diariesByDate.get(i).getSavingDate());
        }
        return new ResponseEntity<>(diaryResponseFormList, HttpStatus.CREATED);
    }


}
