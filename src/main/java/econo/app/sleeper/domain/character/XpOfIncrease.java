package econo.app.sleeper.domain.character;

import lombok.Getter;

import java.util.Arrays;

public enum XpOfIncrease {

    ONE(1L,0,0),
    TWO(2L,5,2),
    THREE(3L,10,5),
    FOUR(4L,15,10),
    FIVE(5L,20,15)
    ;

    private final Long level;
    private final Integer FixedBonusXp;
    private final Integer percentOfBonusXp;

    XpOfIncrease(Long level, Integer fixedBonusXp, Integer percentOfBonusXp) {
        this.level = level;
        this.FixedBonusXp = fixedBonusXp;
        this.percentOfBonusXp = percentOfBonusXp;
    }

    public static Integer getFixedBonusXpOfLevel(Long level){
        return Arrays.stream(XpOfIncrease.values())
                .filter(x -> x.level.equals(level))
                .findAny()
                .orElse(ONE)
                .FixedBonusXp;
    }

    public static Integer getPercentOfBonusXp(Long level){
        return Arrays.stream(XpOfIncrease.values())
                .filter(x -> x.level.equals(level))
                .findAny()
                .orElse(ONE)
                .percentOfBonusXp;
    }
}
