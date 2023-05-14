package econo.app.sleeper.web.member.dto;

import econo.app.sleeper.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class SignupResponse {

    private final String userId;

    public static SignupResponse toDto(Member member){
        return SignupResponse.builder()
                .userId(member.getMemberId())
                .build();
    }


}