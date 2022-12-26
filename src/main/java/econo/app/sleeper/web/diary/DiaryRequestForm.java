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
public class DiaryRequestForm {

    private final String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime localDateTime; // 사용자의 날짜와 시간을 받음

    @DateTimeFormat(pattern = "yyyy-MM-dd")


    public Diary toEntity(LocalDate localDate,User user){
        return Diary.builder()
                .content(content)
                .localDate(localDate)
                .user(user)
                .build();
    }


}
