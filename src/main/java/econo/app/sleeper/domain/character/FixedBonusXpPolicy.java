package econo.app.sleeper.domain.character;

import org.springframework.stereotype.Component;

@Component
public class FixedBonusXpPolicy implements XpPolicy {
    @Override
    public Integer calculateXp(Integer increasingExperience, Long level) {
        Integer bonusXp = XpOfIncrease.getFixedBonusXpOfLevel(level);
        return bonusXp;
    }

}
