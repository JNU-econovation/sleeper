package econo.app.sleeper.service.diary;

import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.web.diary.dto.DiaryFindDto;
import econo.app.sleeper.web.diary.dto.DiaryRequest;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DiaryServiceTest {

    @Autowired
    private DiaryService diaryService;

    @Test
    public void save() {
        ZonedDateTime writingTime = ZonedDateTime.of(LocalDateTime.of(2023,3,10,01,30), ZoneId.of("Asia/Seoul"));
        DiaryRequest diaryRequest = new DiaryRequest("테스트입니다아",1l,writingTime,1L );
        diaryService.save(diaryRequest);
        List<Diary> diaries = diaryService.findDiaries(1L);
        Assertions.assertThat(diaries.size()).isEqualTo(2);
    }

    @Test
    public void updateDiary() {
        diaryService.updateDiary(1L, "수정했어");
        Diary diary = diaryService.findDiary(1L);
        Assertions.assertThat("수정했어").isEqualTo(diary.getContent());
    }

    @Test
    public void deleteDiary() {
        ZonedDateTime writingTime = ZonedDateTime.of(LocalDateTime.of(2023,3,10,01,30), ZoneId.of("Asia/Seoul"));
        DiaryRequest diaryRequest = new DiaryRequest("테스트입니다아",1l,writingTime,1L );
        diaryService.save(diaryRequest);
        diaryService.deleteDiary(2L);
        List<Diary> diaries = diaryService.findDiaries(1L);
        Assertions.assertThat(diaries.size()).isEqualTo(1);
        
    }
    
    @Test
    public void findDiaryByDate() {
        DiaryFindDto diaryFindDto = DiaryFindDto.of(1L, LocalDate.of(2023, 03, 05));
        Diary diary = diaryService.findDiaryByDate(diaryFindDto);
        LocalDate date = LocalDate.of(2023, 03, 05);
        Assertions.assertThat(date.toString()).isEqualTo(diary.getDiaryDate().toString());
    }
}