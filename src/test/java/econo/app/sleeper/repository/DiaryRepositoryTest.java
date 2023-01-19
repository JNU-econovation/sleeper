package econo.app.sleeper.repository;

import econo.app.sleeper.domain.diary.Content;
import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.domain.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class DiaryRepositoryTest {

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void save() {
        User user = User.builder()
                .userId("sleeper")
                .userPassword("sleeper1234@@")
                .userAge(24L)
                .userNickName("관리자")
                .build();

        userRepository.save(user);

        Diary diary = new Diary(new Content("행복한 하루 되세요"),user);

        diaryRepository.save(diary);
    }

    @Test
    public void delete() {

        User user = User.builder()
                .userId("sleeper")
                .userPassword("sleeper1234@@")
                .userAge(24L)
                .userNickName("관리자")
                .build();

        userRepository.save(user);

        Diary diary = new Diary(new Content("행복한 하루 되세요"),user);

        diaryRepository.save(diary);

        diaryRepository.delete(diary);
    }

    @Test
    // 회원의 감사일기들만 찾기
    public void findAllByPk() {
        User user = User.builder()
                .userId("sleeper")
                .userPassword("sleeper1234@@")
                .userAge(24L)
                .userNickName("관리자")
                .build();

        User user1 = User.builder()
                .userId("sleeper2")
                .userPassword("sleeper1234@@2")
                .userAge(25L)
                .userNickName("관리자2")
                .build();

        userRepository.save(user);
        userRepository.save(user1);

        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        Diary diary = new Diary(new Content("행복한 하루 되세요"),user);
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

        Diary diary1 = new Diary(new Content("행복한 하루 되세요"),user);

        diaryRepository.save(diary);
        diaryRepository.save(diary1);

        Long userPk = user.getId();

        List<Diary> diaries = diaryRepository.findAllByPk(userPk);

        for(Diary diaryy : diaries){
            System.out.println(diaryy.getContent());
        }

    }

}

