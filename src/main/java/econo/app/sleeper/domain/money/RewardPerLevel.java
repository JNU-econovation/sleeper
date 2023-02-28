package econo.app.sleeper.domain.money;

import java.util.Arrays;
import java.util.function.Function;

public enum RewardPerLevel {

    ONE(1L, x -> x*1),
    TWO(2L, x->x*2),
    THREE(3L, x->x*3),
    FOUR(4L, x-> x*4),
    FIVE(5L, x -> x*5);

    private Long level;
    private Function<Integer,Integer> rewardEquation;

    RewardPerLevel(Long level,Function<Integer,Integer> rewardEquation){
        this.level = level;
        this.rewardEquation = rewardEquation;
    }

    public static Integer calculateReward(Long level, Integer baseReward){
        return Arrays.stream(RewardPerLevel.values())
                .filter(x -> x.level == level)
                .findAny()
                .orElse(ONE)
                .rewardEquation
                .apply(baseReward);
    }
}
