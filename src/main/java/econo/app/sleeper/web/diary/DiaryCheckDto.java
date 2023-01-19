package econo.app.sleeper.web.diary;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class DiaryCheckDto {

    private final Long diaryPk;
    private final String content;
    private final Boolean existence;

    public static DiaryCheckDto of(Long diaryPk,String content,Boolean existence){
        return DiaryCheckDto.builder()
                .diaryPk(diaryPk)
                .content(content)
                .existence(existence)
                .build();
    }
}