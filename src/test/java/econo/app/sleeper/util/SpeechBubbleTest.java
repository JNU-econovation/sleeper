package econo.app.sleeper.util;

import econo.app.sleeper.domain.character.SpeechBubble;
import org.junit.Test;

public class SpeechBubbleTest {

    @Test
    public void test1(){
        System.out.println("message = " + SpeechBubble.NO_SLEEP.message() );
    }

}