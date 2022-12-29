package econo.app.sleeper.service.sleep;

import econo.app.sleeper.domain.Sleep;
import econo.app.sleeper.repository.SleepRepository;
import econo.app.sleeper.util.Converter;
import econo.app.sleeper.web.sleep.SetTimeRequestForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SleepService {

    private final SleepRepository sleepRepository;

    @Transactional
    public Sleep saveSetTime(SetTimeRequestForm setTimeRequestForm){
        Sleep sleep = SetTimeRequestForm.toEntity(Converter.convertToZoneDateTime(setTimeRequestForm.getSetSleepTime()),
                Converter.convertToZoneDateTime(setTimeRequestForm.getSetWakeTime()));
        sleepRepository.save(sleep);
        return sleep;
    }
}
