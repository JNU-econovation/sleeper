package econo.app.sleeper.service.member;

import econo.app.sleeper.domain.member.Member;
import econo.app.sleeper.web.member.dto.SignUpRequest;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void join() {
        SignUpRequest signUpRequest = new SignUpRequest("aaa", "aaa12", "아이으", 24L, LocalTime.of(02, 00),LocalTime.of(07, 20),LocalTime.of(5, 20));
        Member member = memberService.join(signUpRequest);
        Assertions.assertThat(member.getMemberId()).isEqualTo("aaa");
    }

}