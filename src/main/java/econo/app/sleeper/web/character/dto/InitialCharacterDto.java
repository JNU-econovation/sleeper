package econo.app.sleeper.web.character.dto;

import econo.app.sleeper.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class InitialCharacterDto {

    private final Member member;

    public static InitialCharacterDto of(Member member){
        return InitialCharacterDto.builder()
                .member(member)
                .build();
    }

}
