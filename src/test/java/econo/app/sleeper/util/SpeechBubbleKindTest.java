package econo.app.sleeper.util;

import econo.app.sleeper.domain.SpeechBubbleKind;
import org.junit.Test;

public class SpeechBubbleKindTest {

    @Test
    public void test1(){
        System.out.println("message = " + SpeechBubbleKind.NO_SLEEP.message() );
    }

}