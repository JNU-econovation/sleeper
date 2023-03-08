package econo.app.sleeper.domain.character;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PercentBonusXpPolicy implements XpPolicy {
    @Override
    public Integer calculateXp(Integer increasingExperience, Long level) {
        Integer percentOfBonusXp = XpOfIncrease.getPercentOfBonusXp(level);
        long bonusXp = Math.round(increasingExperience * ((100 + percentOfBonusXp)/100));
        return (int)bonusXp;
    }
}
