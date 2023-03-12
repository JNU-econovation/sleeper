package econo.app.sleeper.web.diary;
import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.service.diary.DiaryService;
import econo.app.sleeper.service.money.MoneyService;
import econo.app.sleeper.web.common.CommonRequest;
import econo.app.sleeper.web.common.CommonResponse;
import econo.app.sleeper.web.diary.dto.DiaryEditRequest;
import econo.app.sleeper.web.diary.dto.DiaryFindDto;
import econo.app.sleeper.web.diary.dto.DiaryRequest;
import econo.app.sleeper.web.diary.dto.DiaryResponse;
import econo.app.sleeper.web.money.MoneyDto;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "diary", description = "감사일기 관련 API")
public class DiaryController {

    private final DiaryService diaryService;
    private final MoneyService moneyService;

    @Operation(summary = "api simple explain", description = "api specific explain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })

    @PostMapping("/diaries")
    public ResponseEntity<CommonResponse> saveDiary(@RequestBody @Valid DiaryRequest diaryRequest, HttpServletResponse response) {
        diaryService.save(diaryRequest);
        moneyService.plusCash(MoneyDto.of(diaryRequest.getUserPk(),diaryRequest.getLevel()));
        Cookie idCookie = new Cookie("content", String.valueOf(diaryRequest.getContent()));
        response.addCookie(idCookie);
        CommonResponse commonResponse = CommonResponse.of("감사일기 작성 완료");
        return new ResponseEntity<>(commonResponse,HttpStatus.CREATED);
    }


    @PutMapping("/diaries/{nu}")
    public ResponseEntity<CommonResponse> updateDiary(@PathVariable("nu") Long diaryPk, @RequestBody @Valid DiaryEditRequest diaryEditRequest){
        diaryService.updateDiary(diaryPk, diaryEditRequest.getContent());
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
        DiaryResponse diaryResponse = DiaryResponse.of(diary.getContent(),diary.getWritingTime());
        return new ResponseEntity<>(diaryResponse,HttpStatus.OK);
    }

    @GetMapping("/diaries/{date}")
    public ResponseEntity<DiaryResponse> findDiaryByDate(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date") LocalDate date,
                                                             @Valid CommonRequest commonRequest) {
        DiaryFindDto diaryFindDto = DiaryFindDto.of(commonRequest.getUserPk(), date);
        Diary diary = diaryService.findDiaryByDate(diaryFindDto);
        DiaryResponse diaryResponse = DiaryResponse.of(diary.getContent(),diary.getWritingTime());
        return new ResponseEntity<>(diaryResponse,HttpStatus.OK);
    }

}
