package econo.app.sleeper.domain.money;

import econo.app.sleeper.domain.character.XpPerLevel;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Function;

@Component
@Primary
public class FixedRewardPolicy implements RewardPolicy{

    private final Integer baseReward = 100;

    @Override
    public Integer decideReward(Long level) {
        return RewardPerLevel.calculateReward(level, baseReward);
    }

}
