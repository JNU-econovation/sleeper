package econo.app.sleeper.service.member;

import econo.app.sleeper.domain.member.Member;
import econo.app.sleeper.domain.member.RoleType;
import econo.app.sleeper.web.member.dto.SignUpRequest;
import econo.app.sleeper.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member join(SignUpRequest signUpRequest) {
        Member member = Member.createMember(signUpRequest.getMemberId(), signUpRequest.getMemberPassword(), signUpRequest.getMemberNickName(), signUpRequest.getMemberAge(), RoleType.USER);
        memberRepository.save(member);
        return member;
    }

    public String idCheck(String userId) {
        Optional<Member> duplicateId = memberRepository.findById(userId);
        if(duplicateId.isPresent()) {return "중복";}
        else return "유효";
    }

}


