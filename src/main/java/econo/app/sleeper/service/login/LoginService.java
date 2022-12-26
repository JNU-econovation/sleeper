package econo.app.sleeper.service.login;

import econo.app.sleeper.domain.User;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.web.login.LoginRequestForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {
    private final UserRepository userRepository;

    // return null이면 로그인 실패함
    public User login(LoginRequestForm loginRequestForm){
        return userRepository.findById(loginRequestForm.getUserId())
                .filter(u -> u.getUserPassword().equals(loginRequestForm.getUserPassword()))
                .orElse(null);
    }

}
