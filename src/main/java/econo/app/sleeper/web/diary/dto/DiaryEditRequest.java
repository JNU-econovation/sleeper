package econo.app.sleeper.web.diary.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class DiaryEditRequest {

    @NotNull
    private final String content;

}
