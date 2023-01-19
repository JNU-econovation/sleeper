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
<<<<<<< HEAD
}
=======
}
>>>>>>> 13fb79243c65d393812d75d293722ba4e115f0b9
