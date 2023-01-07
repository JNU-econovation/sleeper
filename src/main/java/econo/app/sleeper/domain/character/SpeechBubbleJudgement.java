package econo.app.sleeper.domain.character;

public class SpeechBubbleJudgement {

    public static String judgeSpeechBubble(String content){

        String speechBubble = "";

        if(content.length() == 0){
            speechBubble = SpeechBubble.NO_CONTENT.message();
            return speechBubble;
        }
        speechBubble = SpeechBubble.BEFORE_SLEEP.message();
        return speechBubble;
    }

}
