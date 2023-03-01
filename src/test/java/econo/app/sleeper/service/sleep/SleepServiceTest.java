package econo.app.sleeper.service.sleep;

import econo.app.sleeper.domain.character.CharacterRepository;
import econo.app.sleeper.domain.sleep.SleepRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class SleepServiceTest {

    @Autowired
    SleepRepository sleepRepository;

    @Autowired
    SleepService sleepService;

    @Autowired
    CharacterRepository characterRepository;




}