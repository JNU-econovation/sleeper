package econo.app.sleeper.service.character;

import econo.app.sleeper.domain.character.Character;
import econo.app.sleeper.domain.character.Status;
import econo.app.sleeper.domain.character.Growth;
import econo.app.sleeper.domain.common.SpeechBubble;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.exception.RestApiException;
import econo.app.sleeper.exception.error.CommonErrorCode;
import econo.app.sleeper.repository.CharacterRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.web.character.CharacterDto;
import econo.app.sleeper.web.character.CharacterResponse;
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

    private final SpeechBubble speechBubble;

    @Transactional
    public void createCharacter(NewCharacterDto newCharacterDto){
        characterRepository.save(Character.createCharacter(newCharacterDto.getUser()));
    }

    public CharacterResponse readCharacter(Long userPk){
        User user = userRepository.find(userPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        Character character = user.getCharacter();
        CharacterResponse characterResponse = CharacterResponse.builder()
                .color(character.getColor())
                .status(character.getStatus())
                .growth(character.getGrowth())
                .speechBubble(speechBubble.getSpeechBubble())
                .build();
        return characterResponse;
    }

    @Transactional
    public Long updateGrowthAndStatus(CharacterDto characterDto) {
        User user = userRepository.find(characterDto.getUserPk())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        Character character = user.getCharacter();
        Growth growth = character.getGrowth().growUp(characterDto.getPlusExperience());
        character.updateGrowthAndStatus(growth,Status.SLEEP);
        return character.getId();
    }

    @Transactional
    public void updateStatusToSleep(Long userPk){
        User user = userRepository.find(userPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        Character character = user.getCharacter();
        character.updateStatusToSleep();
    }

    public Boolean approachLevel(Long characterPk){
        Character character = characterRepository.findByPk(characterPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        return character.getGrowth().approachLevel();
    }

}
