package econo.app.sleeper.repository;

import econo.app.sleeper.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback()
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findById() {
        User sleeper = userRepository.findById("sleeper").get();
        System.out.println("sleeper = " + sleeper);
    }
}