package econo.app.sleeper.domain.money;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MoneyRepositoryTest {

    @Autowired
    private MoneyRepository moneyRepository;

    @Test
    public void findRecentMoneyByUser() {
        Money money = moneyRepository.findRecentMoneyByUser(1L).get();
        Assertions.assertThat(money.getHoldingCash()).isEqualTo(0);
    }
}