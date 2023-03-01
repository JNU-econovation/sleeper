package econo.app.sleeper.web.diary.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DiaryEditRequest {

    @NotNull
    private String content;

}
