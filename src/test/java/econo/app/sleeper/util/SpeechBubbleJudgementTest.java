package econo.app.sleeper.util;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpeechBubbleJudgementTest {

    @Test
    public void judgeSpeechBubble1() {
        String speech = SpeechBubbleJudgement.judgeSpeechBubble(45);
        Assertions.assertThat(speech).isEqualTo(SpeechBubbleKind.SOON_LEVEL_UP.message());
    }

    @Test
    public void judgeSpeechBubble2() {
        String speech = SpeechBubbleJudgement.judgeSpeechBubble(25);
        Assertions.assertThat(speech).isEqualTo(SpeechBubbleKind.CAN_DO_IT.message());
    }
}