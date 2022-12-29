package econo.app.sleeper.web.sleep;

import econo.app.sleeper.domain.Sleep;
import econo.app.sleeper.repository.SleepRepository;
import econo.app.sleeper.service.sleep.SleepService;
import econo.app.sleeper.util.Converter;
import econo.app.sleeper.web.diary.DiaryResponse;
import econo.app.sleeper.web.user.GoalTimeRequestForm;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.ZoneId;

@RestController
@RequiredArgsConstructor
@Tag(name = "user", description = "사용자 관련 API")
public class SleepController {

    private final SleepService sleepService;
    private final SleepRepository sleepRepository;

    @PostMapping("/sleeps")
    public ResponseEntity<SleepResponse> saveSetTime(SetTimeRequestForm setTimeRequestForm){
        sleepService.saveSetTime(setTimeRequestForm);
        SleepResponse sleepResponse = SleepResponse.builder()
                .message("수면 설정 시간 저장 완료")
                .build();
        return new ResponseEntity<>(sleepResponse,HttpStatus.CREATED);
    }

}
