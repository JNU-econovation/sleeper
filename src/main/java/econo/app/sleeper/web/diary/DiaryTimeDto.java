package econo.app.sleeper.web.diary;

import econo.app.sleeper.domain.Diary;
import econo.app.sleeper.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class DiaryTimeDto {


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime localDateTime;

    private final String userId;
    private final String content;
    public static DiaryTimeDto of(String userId, String content, LocalDateTime localDateTime){
        return DiaryTimeDto.builder()
                .userId(userId)
                .localDateTime(localDateTime)
                .content(content)
                .build();
    }

    public Diary toEntity(LocalDate localDate,LocalDateTime writingTime, User user) {
        return Diary.builder()
                .content(content)
                .localDate(localDate)
                .writingTime(writingTime)
                .user(user)
                .build();
    }
}
