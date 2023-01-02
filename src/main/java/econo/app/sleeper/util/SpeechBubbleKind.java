package econo.app.sleeper.util;

public enum SpeechBubbleKind {

    NO_CONTENT("내일은 좋은 일이 있을거야!"),

    SLEEP("ZZZZZ.."),
    NO_SLEEP("오늘 하루도 파이팅!"),
    BEFORE_SLEEP("오늘 하루도 고생했어"),
    SOON_LEVEL_UP("레벨업이 얼마 남지 않았어요"),

    CAN_DO_IT("할 수 있어요")
    ;

    private final String message;

    SpeechBubbleKind(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
