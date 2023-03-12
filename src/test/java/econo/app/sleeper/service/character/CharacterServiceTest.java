package econo.app.sleeper.service.character;

import econo.app.sleeper.web.character.CharacterResponse;
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
public class CharacterServiceTest {

    @Autowired
    private CharacterService characterService;

    @Test
    public void readCharacter() {
        String color = characterService.readCharacter(1L).getColor();
        Assertions.assertThat(color).isEqualTo("YELLOW");
    }

    @Test
    public void oppositeStatus() {
        characterService.oppositeStatus(1L);
        String status = characterService.readCharacter(1L).getStatus();
        Assertions.assertThat(status).isEqualTo("SLEEP");
    }

    @Test
    public void updateCharacterXp() {
        characterService.updateCharacterXp(1L,7);
        CharacterResponse characterResponse = characterService.readCharacter(1L);
        Assertions.assertThat(characterResponse.getCumulativeXp()).isGreaterThan(0);
    }
}