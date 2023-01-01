package econo.app.sleeper.service.character;

import econo.app.sleeper.domain.Character;
import econo.app.sleeper.domain.Status;
import econo.app.sleeper.repository.CharacterRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Transactional
    public void updateCharacter(String userId, String speechBubble, Status status) {
        Character character = characterRepository.findByPk(userId);
        character.updateSpeechAndStatus(speechBubble,status);
    }

}
