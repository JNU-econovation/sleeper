package econo.app.sleeper.domain.character;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CharacterRepositoryTest {

    @Autowired
    private CharacterRepository characterRepository;

    @Test
    public void find() {
        Character character = characterRepository.find(1L).get();
        Assertions.assertThat(character.getLevel()).isEqualTo(1L);
    }
}