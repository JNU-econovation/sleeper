package econo.app.sleeper.service.money;

import econo.app.sleeper.domain.Money;
import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.repository.MoneyRepository;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.domain.RewardManager;
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
        Integer reward = new RewardManager(diaryRewardDto.getContent().length()).reward();
        Money money = moneyRepository.findRecentMoneyByUser(diaryRewardDto.getUserPk());
        moneyRepository.save(money.use(reward));
    }

    @Transactional
    public void init(InitialMoneyDto initialMoneyDto){
        moneyRepository.save(Money.initMoney(initialMoneyDto.getUser()));
    }

}
