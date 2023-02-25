package econo.app.sleeper.domain.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@Component
public class Notification {

    private String speechBubble;

    public void judgeSpeechBubbleAfterSaveDiary(Integer contentLength){ //감사일기 작성 후 사용
        if(contentLength == 0){
            speechBubble =  Message.NO_CONTENT.getMessage();
        }

    }

    public void judgeSpeechBubbleAfterWakeUp(Boolean levelUpPotential){// 기상 후 사용
        if(levelUpPotential){
            speechBubble =   Message.SOON_LEVEL_UP.getMessage();
        }
        speechBubble =   Message.NO_SLEEP.getMessage();
    }

    public void afterSettingSetTime(){
        speechBubble = Message.BEFORE_SLEEP.getMessage();
    }

}
