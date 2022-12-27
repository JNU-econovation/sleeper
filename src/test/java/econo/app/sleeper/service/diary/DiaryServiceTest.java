package econo.app.sleeper.service.diary;

import econo.app.sleeper.domain.Diary;
import econo.app.sleeper.domain.User;
import econo.app.sleeper.repository.DiaryRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.web.diary.DiaryDateDto;
import econo.app.sleeper.web.diary.DiaryTimeDto;
import econo.app.sleeper.web.diary.DiaryRequestForm;
import econo.app.sleeper.web.diary.DiaryResponse;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class DiaryServiceTest {

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    DiaryService diaryService;

    @Test
    public void saveDiary() {

        DiaryTimeDto diaryTimeDto = DiaryTimeDto.builder()
                .userId("sleeper")
                .localDateTime(LocalDateTime.of(2022, 12, 23, 8, 46))
                .content("오늘도 파이팅입니다.")
                .build();

        DiaryResponse diaryResponse = diaryService.saveDiary(diaryTimeDto);

        DiaryDateDto diaryDateDto = DiaryDateDto.of("sleeper", LocalDate.of(2022, 12, 23));

        List<Diary> diariesByDate = diaryService.findDiariesByDate(diaryDateDto);
        User user = userRepository.findById("sleeper").get();

        Assertions.assertThat("오늘도 파이팅입니다.").isEqualTo(diariesByDate.get(0).getContent());
        Assertions.assertThat(user.getUserMoney()).isGreaterThan(0);
    }

}