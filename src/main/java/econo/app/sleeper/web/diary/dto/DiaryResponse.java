package econo.app.sleeper.web.diary.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class DiaryResponse {

    private final String content;
    private final ZonedDateTime writingTime;

    public static DiaryResponse of(String content, ZonedDateTime writingTime){
        return DiaryResponse.builder()
                .content(content)
                .writingTime(writingTime)
                .build();
    }
}
