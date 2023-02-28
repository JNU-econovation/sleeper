package econo.app.sleeper.domain.money;

import econo.app.sleeper.domain.diary.Diary;
import org.springframework.stereotype.Component;

@Component
public class FlexibleRewardPolicy implements RewardPolicy{

    Diary diary;

    @Override
    public Integer decideReward(Long level) {
        Integer baseReward = getBaseReward(diary.getContentLength());
        return RewardPerLevel.calculateReward(level, baseReward);
    }

    private Integer getBaseReward(Integer diaryContentLength){

        if(diaryContentLength == 0){
            return 0;
        } else if (diaryContentLength < 20) {
            return 100;
        } else if(diaryContentLength <40) {
            return 200;
        }
        return 300;
    }

}
