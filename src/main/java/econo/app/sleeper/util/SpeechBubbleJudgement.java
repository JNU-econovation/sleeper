package econo.app.sleeper.util;

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
    public static String judgeSpeechBubble(Integer experience){
        String speechBubble = "";
        if(ExperienceManager.approachLevel(experience)){
            speechBubble = SpeechBubbleKind.SOON_LEVEL_UP.message();
            return speechBubble;
        }
        speechBubble = SpeechBubbleKind.CAN_DO_IT.message();
        return speechBubble;
    }

}
