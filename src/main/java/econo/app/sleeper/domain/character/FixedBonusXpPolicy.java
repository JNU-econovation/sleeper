package econo.app.sleeper.domain.character;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class FixedBonusXpPolicy extends XpPolicy {
    @Override
    public Integer calculateBonusXp(Integer increasingExperience, Long level) {
        Integer bonusXp = XpOfIncrease.getFixedBonusXpOfLevel(level);
        return bonusXp;
    }

}
