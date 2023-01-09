package econo.app.sleeper.service.login;

import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.web.login.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {
    private final UserRepository userRepository;

    // return null이면 로그인 실패함
    public User login(LoginRequest loginRequest){
        return userRepository.findById(loginRequest.getUserId())
                .filter(u -> u.getUserPassword().equals(loginRequest.getUserPassword()))
                .orElse(null);
    }

}
