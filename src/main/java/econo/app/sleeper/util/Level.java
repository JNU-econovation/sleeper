package econo.app.sleeper.util;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum Level {
    ONE(1L,50),
    TWO(2L,150),
    THREE(3L,300),
    FOUR(4L,500),
    FIVE(5L,750),
    SIX(6L,1050),
    SEVEN(7L,1400)
    ;

    private final Long level;
    private final Integer experience;

    Level(Long level, Integer experience) {
        this.level = level;
        this.experience = experience;
    }

//    public static final Map<Integer, Long> levelByEx = Arrays.stream(Level.values()).collect(Collectors.toMap(l -> l.getExperience(), l -> l.getLevel()));



}
