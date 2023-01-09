package econo.app.sleeper.web.diary;

import econo.app.sleeper.domain.diary.Content;
import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZoneId;
import java.time.ZonedDateTime;

//Setter()를 사용하지 않는 이유 : 계층간 이동시 데이터 변조 위험
@Getter
@RequiredArgsConstructor
@Builder
public class DiaryRequest {


    private final String content;

    private final String userId;

    public Diary toEntity(User user){
        return Diary.builder()
                .user(user)
                .content(new Content(content))
                .build();
    }

}
