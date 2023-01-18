package econo.app.sleeper.domain.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@Component
public class SpeechBubble {

    private String speechBubble;

    public void judgeSpeechBubbleAfterSaveDiary(Integer contentLength){ //감사일기 작성 후 사용

        if(contentLength == 0){
            speechBubble =  SpeechBubbleKind.NO_CONTENT.getMessage();

        }
        speechBubble =  SpeechBubbleKind.SLEEP.getMessage();
    }

    public void judgeSpeechBubbleAfterWakeUp(Boolean levelUpPotential){// 기상 후 사용
        if(levelUpPotential){
            speechBubble =   SpeechBubbleKind.SOON_LEVEL_UP.getMessage();
        }
        speechBubble =   SpeechBubbleKind.NO_SLEEP.getMessage();
    }

    public void afterSettingSetTime(){
        speechBubble = SpeechBubbleKind.BEFORE_SLEEP.getMessage();
    }

}
