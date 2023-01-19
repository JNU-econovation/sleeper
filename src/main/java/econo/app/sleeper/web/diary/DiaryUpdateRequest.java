package econo.app.sleeper.web.diary;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DiaryUpdateRequest {

    @NotNull
    private String content;

}
