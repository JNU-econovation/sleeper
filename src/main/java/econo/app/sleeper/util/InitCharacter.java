package econo.app.sleeper.util;

import econo.app.sleeper.domain.*;
import econo.app.sleeper.domain.Character;

public class InitCharacter {

    public static Character initCharacter(User user){
        return Character.builder()
                .color(Color.GRAY)
                .status(Status.NO_SLEEP)
                .experience(0)
                .level(1L)
                .speechBubble(SpeechBubbleKind.NO_SLEEP.message())
                .user(user)
                .build();
    }
}
