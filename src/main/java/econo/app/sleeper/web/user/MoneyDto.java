package econo.app.sleeper.web.user;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class MoneyDto {

    private final String content;
    private final String userId;

    public static MoneyDto of(String content, String userId){
        return MoneyDto.builder()
                .content(content)
                .userId(userId)
                .build();
    }
}
