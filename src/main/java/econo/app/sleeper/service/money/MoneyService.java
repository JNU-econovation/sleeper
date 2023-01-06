package econo.app.sleeper.service.money;

import econo.app.sleeper.domain.Money;
import econo.app.sleeper.domain.User;
import econo.app.sleeper.repository.MoneyRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.util.RewardManager;
import econo.app.sleeper.web.diary.DiaryRewardDto;
import econo.app.sleeper.web.money.InitialMoneyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MoneyService {

    private final MoneyRepository moneyRepository;
    private final UserRepository userRepository;

    @Transactional
    public void obtain(DiaryRewardDto diaryRewardDto){
        User user = userRepository.find(diaryRewardDto.getUserPk()).get();
        Integer reward = new RewardManager(diaryRewardDto.getContent()).reward();
        Integer holdingMoney = moneyRepository.findRecentDiaryByUser(diaryRewardDto.getUserPk()).getHoldingMoney();
        Money money = Money.builder().build();
        moneyRepository.save(money.use(reward,holdingMoney,user));
    }

    @Transactional
    public void init(InitialMoneyDto initialMoneyDto){
        moneyRepository.save(Money.initMoney(initialMoneyDto.getUser()));
    }


}
