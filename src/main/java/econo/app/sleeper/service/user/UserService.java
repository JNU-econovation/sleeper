package econo.app.sleeper.service.user;

import econo.app.sleeper.domain.user.RoleType;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.service.character.CharacterService;
import econo.app.sleeper.service.money.MoneyService;
import econo.app.sleeper.web.character.NewCharacterDto;
import econo.app.sleeper.web.money.InitialMoneyDto;
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
    private final CharacterService characterService;
    private final MoneyService moneyService;

    @Transactional
    public User join(SignUpRequest signUpRequest) {
        User user = User.create(signUpRequest.getUserId(), signUpRequest.getUserPassword(), signUpRequest.getUserNickName(), signUpRequest.getUserAge(), RoleType.USER);
        userRepository.save(user);
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

