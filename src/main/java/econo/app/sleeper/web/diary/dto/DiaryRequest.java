package econo.app.sleeper.web.diary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import econo.app.sleeper.domain.diary.Diary;
import econo.app.sleeper.domain.member.Member;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;


@Getter
@RequiredArgsConstructor
public class DiaryRequest {

    private final String content;

    @NotNull
    private final Long userPk;

    @JsonProperty("writingDiaryTime")
    private final ZonedDateTime writingDiaryTime;

    @NotNull
    private final Long level;

    public Diary toEntity(String content, Member member){
        return Diary.builder()
                .member(member)
                .content(content)
                .build();
    }

}
