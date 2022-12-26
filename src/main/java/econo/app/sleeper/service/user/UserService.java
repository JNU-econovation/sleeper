package econo.app.sleeper.service.user;

import econo.app.sleeper.domain.User;
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

    @Transactional
    public User join(SignUpRequestForm signUpRequestForm) {
        User user = signUpRequestForm.toEntity();
        // ID 중복체크 구현
        userRepository.save(user);
        return user;
    }



}

