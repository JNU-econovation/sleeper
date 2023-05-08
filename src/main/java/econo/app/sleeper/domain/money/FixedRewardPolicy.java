package econo.app.sleeper.domain.money;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FixedRewardPolicy implements RewardPolicy{

    private final Integer baseReward = 100;

    @Override
    public Integer decideReward(Long level) {
        return RewardPerLevel.calculateReward(level, baseReward);
    }

}
