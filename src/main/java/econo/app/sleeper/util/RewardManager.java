package econo.app.sleeper.util;

import econo.app.sleeper.domain.Money;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RewardManager {

    private final String content;

    public Integer reward(){

        Integer length = content.length();

        if(length<1){
            return RewardStage.BRONZE_REWARD.getReward();
        }else if(length < 10){
            return RewardStage.SILVER_REWARD.getReward();
        }else{
            return RewardStage.BRONZE_REWARD.getReward();
        }
    }

    @RequiredArgsConstructor
    @Getter
    private enum RewardStage{
        GOLD_REWARD(1,10),
        SILVER_REWARD(2,5),
        BRONZE_REWARD(3,1);

        private final Integer grade;
        private final Integer reward;
    }



}
