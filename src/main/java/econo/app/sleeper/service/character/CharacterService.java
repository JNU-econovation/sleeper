package econo.app.sleeper.service.character;

import econo.app.sleeper.domain.character.Character;
import econo.app.sleeper.domain.character.Status;
import econo.app.sleeper.domain.character.Growth;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.repository.CharacterRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.web.character.CharacterDto;
import econo.app.sleeper.web.character.NewCharacterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createCharacter(NewCharacterDto newCharacterDto){
        characterRepository.save(Character.createCharacter(newCharacterDto.getUser()));
    }

    @Transactional
    public Long updateGrowthAndStatus(CharacterDto characterDto) {// dto로 plusExperience의 정보가 들어가 있어야한다.
        User user = userRepository.find(characterDto.getUserPk()).get();
        Character character = user.getCharacter();
        Growth growth = character.getGrowth().growUp(characterDto.getPlusExperience());
        character.updateGrowthAndStatus(growth,Status.SLEEP);
        return character.getId();
    }

    @Transactional
    public void updateStatusToSleep(Long userPk){
        User user = userRepository.find(userPk).get();
        Character character = user.getCharacter();
        character.updateStatusToSleep();
    }

    public Boolean approachLevel(Long characterPk){
        Character character = characterRepository.findByPk(characterPk).get();
        return character.getGrowth().approachLevel();
    }

}
