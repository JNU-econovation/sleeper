package econo.app.sleeper.web.diary;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DiaryContinueRequest {

    @NotNull
    private String content;

    @NotNull
    private Long userPk;
}
