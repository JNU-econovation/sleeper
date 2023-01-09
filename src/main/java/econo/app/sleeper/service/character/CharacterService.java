package econo.app.sleeper.service.character;

import econo.app.sleeper.domain.character.Character;
import econo.app.sleeper.domain.Sleep;
import econo.app.sleeper.domain.character.Status;
import econo.app.sleeper.domain.character.Growth;
import econo.app.sleeper.domain.diary.Content;
import econo.app.sleeper.repository.CharacterRepository;
import econo.app.sleeper.repository.SleepRepository;
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

    @Transactional
    public void init(NewCharacterDto newCharacterDto){
        characterRepository.save(Character.initCharacter(newCharacterDto.getUser()));
    }

    @Transactional
    public void update(CharacterDto characterDto) {
        Character character = characterRepository.findById(characterDto.getUserId()).get();
        character.updateCharacter(new Content(characterDto.getContent()).judgeSpeechBubble(),Status.SLEEP);
    }

    @Transactional
    public void update(SleepCharacterDto sleepCharacterDto){
        Character character = characterRepository.findById(sleepCharacterDto.getUserId()).get();
        Sleep sleep = sleepRepository.findByPk(sleepCharacterDto.getSleepPk()).get();
        Integer plusExperience = sleep.assessExperience(sleep.getSetSleepTime(), sleep.getSetWakeTime(), sleep.getActualSleepTime(), sleep.getActualWakeTime());
        Growth growth = character.getGrowth().growth(plusExperience);
        character.updateCharacter(growth,Status.NO_SLEEP,growth.judgeSpeechBubble());
    }

}
