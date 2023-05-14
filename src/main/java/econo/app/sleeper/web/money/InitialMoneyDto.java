package econo.app.sleeper.web.money;

import econo.app.sleeper.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class InitialMoneyDto {

    private final Member member;

    public static InitialMoneyDto of(Member member){
        return InitialMoneyDto.builder()
                .member(member)
                .build();
    }

}
