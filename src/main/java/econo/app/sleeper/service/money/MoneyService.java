package econo.app.sleeper.service.money;

import econo.app.sleeper.domain.Character;
import econo.app.sleeper.domain.Diary;
import econo.app.sleeper.domain.Money;
import econo.app.sleeper.repository.DiaryRepository;
import econo.app.sleeper.repository.MoneyRepository;
import econo.app.sleeper.web.character.NewCharacterDto;
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
    private final DiaryRepository diaryRepository;

    @Transactional
    public void get(DiaryRewardDto diaryRewardDto){
        Diary diary = diaryRepository.findByPk(diaryRewardDto.getDiaryPk()).get();
        Money money = diary.reward(diaryRewardDto.getContent());
        moneyRepository.save(money);
    }

    @Transactional
    public void init(InitialMoneyDto initialMoneyDto){
        moneyRepository.save(Money.initMoney(initialMoneyDto.getUser()));
    }


}
