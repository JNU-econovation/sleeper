package econo.app.sleeper.web.common;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CommonRequest {

    @NotNull(message = "userPk는 필수 입력값입니다.")
    private Long userPk;

}
