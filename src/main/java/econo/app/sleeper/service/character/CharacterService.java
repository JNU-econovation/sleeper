package econo.app.sleeper.service.character;

import econo.app.sleeper.domain.character.Character;
import econo.app.sleeper.domain.sleep.Sleep;
import econo.app.sleeper.domain.character.Status;
import econo.app.sleeper.domain.character.Growth;
import econo.app.sleeper.domain.diary.Content;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.repository.CharacterRepository;
import econo.app.sleeper.repository.SleepRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.web.character.CharacterDto;
import econo.app.sleeper.web.character.NewCharacterDto;
import econo.app.sleeper.web.sleep.SleepCharacterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final SleepRepository sleepRepository;

    private final UserRepository userRepository;

    @Transactional
    public void init(NewCharacterDto newCharacterDto){
        characterRepository.save(Character.init(newCharacterDto.getUser()));
    }

    @Transactional
    public void update(CharacterDto characterDto) {
        Character character = characterRepository.findByPk(characterDto.getUserPk()).get();
        character.updateCharacter(new Content(characterDto.getContent()).judgeSpeechBubble(),Status.SLEEP);
    }

    @Transactional
    public void update(SleepCharacterDto sleepCharacterDto){
        User user = userRepository.find(sleepCharacterDto.getUserPk()).get();
        Character character = user.getCharacter();
        Sleep sleep = user.getSleeps().stream()
                .filter(s -> s.getSleepPk().equals(sleepCharacterDto.getSleepPk()))
                .findFirst()
                .get();
        Integer plusExperience = sleep.assessExperience(sleep.getSetTime().getSetSleepTime(), sleep.getSetTime().getSetWakeTime(), sleep.getSavingDate().getSavingDateTime(), sleep.getActualWakeTime());
        Growth growth = character.getGrowth().growth(plusExperience);
        character.updateCharacter(growth,Status.NO_SLEEP,growth.judgeSpeechBubble());
    }

}
