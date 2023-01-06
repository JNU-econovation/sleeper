package econo.app.sleeper.service.user;

import econo.app.sleeper.domain.User;
import econo.app.sleeper.repository.CharacterRepository;
import econo.app.sleeper.web.user.GoalTimeDto;
import econo.app.sleeper.web.user.SignUpRequest;
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
    public User join(SignUpRequest signUpRequest) {
        User user = signUpRequest.toEntity();
        userRepository.save(user);
        // ID 중복체크 구현
        return user;
    }

    @Transactional
    public void updateGoalTime(GoalTimeDto goalTimeDto){
        User user = userRepository.findById(goalTimeDto.getUserId()).get();
        user.update(goalTimeDto.getGoalSleepTime(),goalTimeDto.getGoalWakeTime());
    }

    public User readGoalTime(String userId) {
        return userRepository.findById(userId).get();
    }


}

