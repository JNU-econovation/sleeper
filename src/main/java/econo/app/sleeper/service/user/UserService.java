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

    @Transactional
    public User join(SignUpRequest signUpRequest) {
        User user = User.create(signUpRequest.getUserId(), signUpRequest.getUserPassword(), signUpRequest.getUserNickName(), signUpRequest.getUserAge(), RoleType.USER, signUpRequest.getGoalSleepTime(), signUpRequest.getGoalWakeTime());
        userRepository.save(user);
        return user;
    }

    public User readGoalTime(Long userPk) {
        return userRepository.find(userPk).get();
    }


}

