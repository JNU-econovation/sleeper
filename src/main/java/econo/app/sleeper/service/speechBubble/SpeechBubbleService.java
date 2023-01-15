package econo.app.sleeper.service.speechBubble;

import econo.app.sleeper.domain.common.SpeechBubble;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpeechBubbleService {

    private final SpeechBubble speechBubble;

    public void judgeSpeechBubbleAfterSaveDiary(Integer contentLength){
        speechBubble.judgeSpeechBubbleAfterSaveDiary(contentLength);
    }

    public void judgeSpeechBubbleAfterWakeUp(Boolean levelUpPotential){
        speechBubble.judgeSpeechBubbleAfterWakeUp(levelUpPotential);
    }

    public void afterSettingSetTime(){
        speechBubble.afterSettingSetTime();
    }

}
