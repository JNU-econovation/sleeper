package econo.app.sleeper.service.user;

import econo.app.sleeper.domain.User;
import econo.app.sleeper.repository.CharacterRepository;
import econo.app.sleeper.util.InitCharacter;
import econo.app.sleeper.web.user.GoalTimeDto;
import econo.app.sleeper.web.user.SignUpRequestForm;
import econo.app.sleeper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final CharacterRepository characterRepository;

    @Transactional
    public User join(SignUpRequestForm signUpRequestForm) {
        User user = signUpRequestForm.toEntity();
        // ID 중복체크 구현
        userRepository.save(user);
        // 케릭터 생성
        characterRepository.save(InitCharacter.initCharacter(user));
        return user;
    }

    @Transactional
    public void updateGoalTime(GoalTimeDto goalTimeDto){
        User user = userRepository.findById(goalTimeDto.getUserId()).get();
        user.updateGoalTime(goalTimeDto.getGoalSleepTime(),goalTimeDto.getGoalWakeTime());
    }

    public User readGoalTime(String userId){
        return userRepository.findById(userId).get();
    }


}

