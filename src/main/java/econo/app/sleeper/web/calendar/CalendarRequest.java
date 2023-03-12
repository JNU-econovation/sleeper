package econo.app.sleeper.web.calendar;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@Builder
public class CalendarRequest {

    @NotNull(message = "userPk는 필수 입력값입니다.")
    private final Long userPk;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate nowDate;

}
