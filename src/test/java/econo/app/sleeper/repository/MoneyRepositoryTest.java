package econo.app.sleeper.repository;

import econo.app.sleeper.domain.money.Money;
import econo.app.sleeper.domain.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class MoneyRepositoryTest {

    @Autowired
    MoneyRepository moneyRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void findRecentMoneyByUser() {

        User sleeper = userRepository.findById("sleeper").get();
        Money money = moneyRepository.findRecentMoneyByUser(sleeper.getUserPk());
        Money money1 = money.use(10);
        moneyRepository.save(money1);
    }
}