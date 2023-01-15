package econo.app.sleeper.service.money;

import econo.app.sleeper.domain.money.Deal;
import econo.app.sleeper.domain.diary.Content;
import econo.app.sleeper.repository.MoneyRepository;
import econo.app.sleeper.repository.UserRepository;
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
    public Integer obtainMoney(DiaryRewardDto diaryRewardDto){
        Integer reward = new Content(diaryRewardDto.getContent()).reward();
        Deal moneyDeal = moneyRepository.findRecentMoneyByUser(diaryRewardDto.getUserPk());
        moneyRepository.save(moneyDeal.plusMoney(reward));
        return reward;
    }

    @Transactional
    public void createMoney(InitialMoneyDto initialMoneyDto){
        moneyRepository.save(Deal.createMoney(initialMoneyDto.getUser()));
    }

}
