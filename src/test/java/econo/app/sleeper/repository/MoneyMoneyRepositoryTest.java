package econo.app.sleeper.repository;

import econo.app.sleeper.domain.money.Money;
import econo.app.sleeper.domain.money.MoneyRepository;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.domain.user.UserRepository;
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
public class MoneyMoneyRepositoryTest {

    @Autowired
    MoneyRepository moneyRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void findRecentMoneyByUser() {

        User sleeper = userRepository.findById("sleeper").get();
        Money moneyMoney = moneyRepository.findRecentMoneyByUser(sleeper.getId()).get();
        Money moneyMoney1 = moneyMoney.plusCash(10);
        moneyRepository.save(moneyMoney1);
    }
}