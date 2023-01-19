package econo.app.sleeper.web.diary;

import econo.app.sleeper.domain.diary.Content;
import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.domain.user.User;
import lombok.*;
import javax.validation.constraints.NotNull;


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
