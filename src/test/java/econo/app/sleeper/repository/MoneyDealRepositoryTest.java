package econo.app.sleeper.repository;

import econo.app.sleeper.domain.money.Deal;
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
public class MoneyDealRepositoryTest {

    @Autowired
    MoneyRepository moneyRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void findRecentMoneyByUser() {

        User sleeper = userRepository.findById("sleeper").get();
        Deal moneyDeal = moneyRepository.findRecentMoneyByUser(sleeper.getId());
        Deal moneyDeal1 = moneyDeal.plusMoney(10);
        moneyRepository.save(moneyDeal1);
    }
}