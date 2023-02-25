package econo.app.sleeper.service.speechBubble;

import econo.app.sleeper.domain.common.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpeechBubbleService {

    private final Notification notification;

    public void judgeSpeechBubbleAfterSaveDiary(Integer contentLength){
        notification.judgeSpeechBubbleAfterSaveDiary(contentLength);
    }

    public void judgeSpeechBubbleAfterWakeUp(Boolean levelUpPotential){
        notification.judgeSpeechBubbleAfterWakeUp(levelUpPotential);
    }

    public void afterSettingSetTime(){
        notification.afterSettingSetTime();
    }

}
