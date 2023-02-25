package econo.app.sleeper.service.user;

import com.sun.xml.bind.v2.TODO;
import econo.app.sleeper.domain.user.RoleType;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.exception.RestApiException;
import econo.app.sleeper.exception.error.CommonErrorCode;
import econo.app.sleeper.web.user.IdRequest;
import econo.app.sleeper.web.user.SignUpRequest;
import econo.app.sleeper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User join(SignUpRequest signUpRequest) {
        User user = User.createUser(signUpRequest.getUserId(), signUpRequest.getUserPassword(), signUpRequest.getUserNickName(), signUpRequest.getUserAge(), RoleType.USER);
        // TODO: 2023-02-25 MODEL MAPPER 공부
        userRepository.save(user);
        return user;
    }

    public User readGoalTime(Long userPk) {
        return userRepository.find(userPk)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
    }

    public String idCheck(String userId) {
        Optional<User> duplicateId = userRepository.findById(userId);
        if(duplicateId.isPresent()) {return "중복";}
        else return "유효";
    }
}


