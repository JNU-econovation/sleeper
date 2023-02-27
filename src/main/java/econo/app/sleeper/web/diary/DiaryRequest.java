package econo.app.sleeper.web.diary;

import com.fasterxml.jackson.annotation.JsonProperty;
import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.domain.user.User;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryRequest {

    private String content;

    @NotNull
    private Long userPk;

    @JsonProperty("actualWakeTime")
    private ZonedDateTime writingDiaryTime;

    public Diary toEntity(String content,User user){
        return Diary.builder()
                .user(user)
                .content(content)
                .build();
    }

}
