package econo.app.sleeper.domain.diary;

import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.domain.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
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
public class DiaryRepositoryTest {

    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void find() {
        Diary diary = diaryRepository.find(1L).get();
        Assertions.assertThat(diary.getContent()).isEqualTo("오늘도 너무나 행복한 하루였습니다!!");
    }

    @Test
    public void findAll() {
        User user = userRepository.find(1L).get();
        ZonedDateTime dateTime = ZonedDateTime.of(LocalDateTime.of(2023, 03, 05, 01, 33), ZoneId.of("Asia/Seoul"));
        Diary diary = Diary.create("안녕하세요", LocalDate.of(2023, 03, 04), dateTime, user);
        diaryRepository.save(diary);
        List<Diary> diaries = diaryRepository.findAll(1L);
        Assertions.assertThat(diaries.size()).isEqualTo(2);
    }

    @Test
    public void findRecentDiaryByUser() {
        User user = userRepository.find(1L).get();
        ZonedDateTime dateTime = ZonedDateTime.of(LocalDateTime.of(2023, 03, 05, 01, 33), ZoneId.of("Asia/Seoul"));
        Diary diary = Diary.create("안녕하세요", LocalDate.of(2023, 03, 04), dateTime, user);
        diaryRepository.save(diary);
        Diary recentDiary = diaryRepository.findRecentDiaryByUser(1L);
        Assertions.assertThat(recentDiary.getContent()).isEqualTo("오늘도 너무나 행복한 하루였습니다!!");
    }

    @Test
    public void findDiaryByDate() {
        Diary diary = diaryRepository.findDiaryByDate(1L, LocalDate.of(2023, 3, 5)).get();
        Assertions.assertThat(diary.getContent()).isEqualTo("오늘도 너무나 행복한 하루였습니다!!");
    }


    @Test
    public void findDiaryBetweenDates() {
        User user = userRepository.find(1L).get();
        ZonedDateTime dateTime = ZonedDateTime.of(LocalDateTime.of(2023, 03, 05, 01, 33), ZoneId.of("Asia/Seoul"));
        Diary diary = Diary.create("안녕하세요", LocalDate.of(2023, 03, 04), dateTime, user);
        diaryRepository.save(diary);
        List<Diary> diaries = diaryRepository.findDiaryBetweenDates(1L, LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 31));
        Assertions.assertThat(diaries.size()).isEqualTo(2L);
    }
}