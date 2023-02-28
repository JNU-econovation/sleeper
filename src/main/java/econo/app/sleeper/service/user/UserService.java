package econo.app.sleeper.service.user;

import econo.app.sleeper.domain.user.RoleType;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.web.user.dto.SignUpRequest;
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
        userRepository.save(user);
        return user;
    }

    public String idCheck(String userId) {
        Optional<User> duplicateId = userRepository.findById(userId);
        if(duplicateId.isPresent()) {return "중복";}
        else return "유효";
    }
}


