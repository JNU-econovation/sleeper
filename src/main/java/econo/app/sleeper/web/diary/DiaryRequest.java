package econo.app.sleeper.web.diary;

import econo.app.sleeper.domain.Diary;
import econo.app.sleeper.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
//Setter()를 사용하지 않는 이유 : 계층간 이동시 데이터 변조 위험
@Getter
@RequiredArgsConstructor
@Builder
public class DiaryRequest {

    private final String content;

}
