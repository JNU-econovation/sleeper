package econo.app.sleeper.web.common;

import lombok.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class CommonRequest {

    @NotNull(message = "userPk는 필수 입력값입니다.")
    private final Long userPk;

}
