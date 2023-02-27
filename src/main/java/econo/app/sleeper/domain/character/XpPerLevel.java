package econo.app.sleeper.domain.character;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum XpPerLevel {

    ONE(1L,100),
    TWO(2L,300),
    THREE(3L,600),
    FOUR(4L,1000),
    FIVE(5L,999999999),
    ;

    private final Long level;
    private final Integer requiredXp;

    XpPerLevel(Long level, Integer requiredXp){
        this.level = level;
        this.requiredXp = requiredXp;
    }

    public static Long getCurrentLevel(Integer cumulativeXp, Long level){
        return Arrays.stream(XpPerLevel.values())
                .filter(x -> x.requiredXp <= cumulativeXp)
                .reduce((first,second) -> second)
                .map(XpPerLevel::getLevel)
                .orElse(level);
    }

}
