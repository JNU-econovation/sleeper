package econo.app.sleeper.service.diary;

import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.repository.DiaryRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.web.diary.DiaryRequest;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class DiaryServiceTest {

    @Autowired
    DiaryService diaryService;

    @Test
    public void saveDiary() {

        DiaryRequest diaryRequest = new DiaryRequest("안녕하세요",1L);
        Long diaryPk = diaryService.save(diaryRequest);

        Diary diary = diaryService.findDiary(diaryPk);
        Assertions.assertThat(diaryPk).isEqualTo(diary.getId());

    }
    
}