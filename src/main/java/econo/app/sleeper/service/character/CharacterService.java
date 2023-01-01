package econo.app.sleeper.service.character;

import econo.app.sleeper.domain.Character;
import econo.app.sleeper.domain.Sleep;
import econo.app.sleeper.domain.Status;
import econo.app.sleeper.repository.CharacterRepository;
import econo.app.sleeper.repository.SleepRepository;
import econo.app.sleeper.util.ExperienceManager;
import econo.app.sleeper.web.sleep.SleepCharacterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final SleepRepository sleepRepository;

    // 잠에 들 때
    @Transactional
    public void updateCharacter(String userId, String speechBubble, Status status) {
        Character character = characterRepository.findById(userId).get();
        character.updateSpeechAndStatus(speechBubble,status);
    }

    // 일어날 때
    @Transactional
    public void updateCharacter(SleepCharacterDto sleepCharacterDto){
        Character character = characterRepository.findById(sleepCharacterDto.getUserId()).get();
        Sleep sleep = sleepRepository.findByPk(sleepCharacterDto.getSleepPk()).get();
        Integer experience = ExperienceManager.assessExperience(sleep.getSetSleepTime(), sleep.getSetWakeTime(), sleep.getActualSleepTime(), sleep.getActualWakeTime());
        Long level = ExperienceManager.convertExToLevel(character.getExperience(), experience);
        character.updateExAndStatus(experience,level,Status.NO_SLEEP);
    }

}
