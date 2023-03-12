package econo.app.sleeper.service.character;

import econo.app.sleeper.domain.character.Character;
import econo.app.sleeper.domain.character.XpPolicy;
import econo.app.sleeper.exception.RestApiException;
import econo.app.sleeper.exception.error.CommonErrorCode;
import econo.app.sleeper.domain.character.CharacterRepository;
import econo.app.sleeper.web.character.CharacterResponse;
import econo.app.sleeper.web.character.dto.InitialCharacterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;

    private final XpPolicy xpPolicy;

    @Autowired
    public CharacterService(XpPolicy xpPolicy, CharacterRepository characterRepository){
        this.xpPolicy = xpPolicy;
        this.characterRepository = characterRepository;
    }

    @Transactional
    public Long createCharacter(InitialCharacterDto initialCharacterDto){
        Long characterPk = characterRepository.save(Character.createCharacter(initialCharacterDto.getUser()));
        return characterPk;
    }

    public CharacterResponse readCharacter(Long characterPk){
        Character character = characterRepository.find(characterPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        CharacterResponse characterResponse = CharacterResponse.of(character.getColor(), character.getStatus(), character.getCumulativeXp(), character.getLevel());
        return characterResponse;
    }

    @Transactional
    public void oppositeStatus(Long characterPk){
        Character character = characterRepository.find(characterPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        character.oppositeStatus();
    }

    @Transactional
    public void updateCharacterXp(Long characterPk, Integer increasingExperience) {
        Character character = characterRepository.find(characterPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        Integer xp = xpPolicy.calculateXp(increasingExperience,character.getLevel());
        character.plusXp(xp);
    }

}
