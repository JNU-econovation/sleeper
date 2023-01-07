package econo.app.sleeper.web.diary;

import econo.app.sleeper.domain.Diary;
import econo.app.sleeper.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

//Setter()를 사용하지 않는 이유 : 계층간 이동시 데이터 변조 위험
@Getter
@RequiredArgsConstructor
@Builder
public class DiaryRequest {


    private final String content;

    private final String userId;

    public Diary toEntity(LocalDate savingDate, User user){
        return Diary.builder()
                .savingDate(savingDate)
                .user(user)
                .writingTime(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
                .content(content)
                .build();
    }

}
