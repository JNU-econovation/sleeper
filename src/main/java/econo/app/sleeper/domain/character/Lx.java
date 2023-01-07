package econo.app.sleeper.domain.character;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Lx {

    ZERO(0L,0),
    ONE(1L,50),
    TWO(2L,150),
    THREE(3L,300),
    FOUR(4L,500),
    FIVE(5L,750),
    SIX(6L,1050),
    SEVEN(7L,1400)
    ;

    private final Long level;
    private final Integer experienceOfLevel;
}
