package econo.app.sleeper.util;

import econo.app.sleeper.domain.Character;
import econo.app.sleeper.domain.Color;
import econo.app.sleeper.domain.Status;
import econo.app.sleeper.domain.User;
import econo.app.sleeper.web.character.CharacterDto;
import econo.app.sleeper.web.diary.DiaryDateDto;

public class InitCharacter {

    public static Character initCharacter(User user){
        return Character.builder()
                .color(Color.GRAY)
                .status(Status.NO_SLEEP)
                .experience(0)
                .level(1L)
                .speechBubble("안녕하세요~")
                .user(user)
                .build();
    }
}
