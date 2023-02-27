package econo.app.sleeper.domain.character;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PercentBonusXpPolicy extends XpPolicy {
    @Override
    public Integer calculateBonusXp(Integer increasingExperience, Long level) {
        Integer percentOfBonusXp = XpOfIncrease.getPercentOfBonusXp(level);
        long bonusXp = Math.round(increasingExperience * ((100 + percentOfBonusXp)/100));
        return (int)bonusXp;
    }
}
