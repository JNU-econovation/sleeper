package econo.app.sleeper.web.diary;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class DiaryParam {

    private final String content;

    public static DiaryParam of(String content){
        return DiaryParam.builder()
                .content(content)
                .build();
    }
}
