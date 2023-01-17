package econo.app.sleeper.web.diary;

import econo.app.sleeper.domain.diary.Content;
import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.domain.user.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZoneId;
import java.time.ZonedDateTime;

//Setter()를 사용하지 않는 이유 : 계층간 이동시 데이터 변조 위험
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryRequest {


    private String content;

    @NotNull
    private Long userPk;

    public Diary toEntity(User user){
        return Diary.builder()
                .user(user)
                .content(new Content(content))
                .build();
    }

}
