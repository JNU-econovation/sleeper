package econo.app.sleeper.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() {
        User user = User.createUser("test", "test", "sleeper", 25L, RoleType.USER);
        userRepository.save(user);
    }

    @Test
    public void find() {
        User user = userRepository.find(1L).get();
        Assertions.assertThat(user.getUserId()).isEqualTo("sleeper");
    }

    @Test
    public void findAll() {
        User user = User.createUser("test", "test", "sleeper", 25L, RoleType.USER);
        userRepository.save(user);
        List<User> users = userRepository.findAll();
        Assertions.assertThat(users.size()).isEqualTo(2);
    }

    @Test
    public void findById() {
        User user = userRepository.findById("sleeper").get();
        Assertions.assertThat(user.getUserPassword()).isEqualTo("sleeper12@@");
    }
}