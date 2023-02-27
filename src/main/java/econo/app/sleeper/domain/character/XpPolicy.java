package econo.app.sleeper.domain.character;

import econo.app.sleeper.domain.sleep.Sleep;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class XpPolicy {

    private Sleep sleep;

    public Integer calculateXp(Long level){
        Integer increasingExperience = sleep.calculateXp();
        Integer bonusExperience = calculateBonusXp(increasingExperience, level);
        return increasingExperience + bonusExperience;
    }

    public abstract Integer calculateBonusXp(Integer increasingExperience, Long level);

}
