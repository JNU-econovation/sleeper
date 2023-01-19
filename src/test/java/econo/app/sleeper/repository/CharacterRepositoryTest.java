package econo.app.sleeper.repository;

import econo.app.sleeper.domain.character.Character;
import org.assertj.core.api.Assertions;
import org.junit.Test;
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
public class CharacterRepositoryTest {

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    UserRepository userRepository;


    @Test
    public void 일대일양방향관계에서_주테이블에_FK를_둘_경우_주테이블에서_객체로_찾는_경우(){

        String userId = "sleeper";
        long startTime = System.nanoTime(); // 코드 시작 시간

        Character character1 = userRepository.findById(userId).get().getCharacter();
        long endTime = System.nanoTime(); // 코드 끝난 시간

        long durationTimeSec = endTime - startTime;
        System.out.println(durationTimeSec + "n/s"); // 나노세컨드 출력


    }

}