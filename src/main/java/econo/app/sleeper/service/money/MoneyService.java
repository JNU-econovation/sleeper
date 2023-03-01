package econo.app.sleeper.service.money;

import econo.app.sleeper.domain.money.Money;
import econo.app.sleeper.domain.money.RewardPolicy;
import econo.app.sleeper.exception.RestApiException;
import econo.app.sleeper.exception.error.CommonErrorCode;
import econo.app.sleeper.domain.money.MoneyRepository;
import econo.app.sleeper.web.money.MoneyDto;
import econo.app.sleeper.web.money.InitialMoneyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MoneyService {

    private final MoneyRepository moneyRepository;

    private final RewardPolicy rewardPolicy;

    @Transactional
    public void createMoney(InitialMoneyDto initialMoneyDto){
        moneyRepository.save(Money.createMoney(initialMoneyDto.getUser()));
    }

    @Transactional
    public Integer plusCash(MoneyDto moneyDto){
        Integer reward = rewardPolicy.decideReward(moneyDto.getLevel());
        Money money = moneyRepository.findRecentMoneyByUser(moneyDto.getUserPk())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        moneyRepository.save(money.plusCash(reward));
        return reward;
    }


}
