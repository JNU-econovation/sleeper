package econo.app.sleeper.domain;

public class SpeechBubbleJudgement {

    public static String judgeSpeechBubble(String content){

        String speechBubble = "";

        if(content.length() == 0){
            speechBubble = SpeechBubbleKind.NO_CONTENT.message();
            return speechBubble;
        }
        speechBubble = SpeechBubbleKind.BEFORE_SLEEP.message();
        return speechBubble;
    }



}
