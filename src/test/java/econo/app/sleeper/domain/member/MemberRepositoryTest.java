package econo.app.sleeper.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void save() {
        Member member = Member.createMember("test", "test", "sleeper", 25L, RoleType.USER);
        memberRepository.save(member);
    }

    @Test
    public void find() {
        Member member = memberRepository.find(1L).get();
        Assertions.assertThat(member.getMemberId()).isEqualTo("sleeper");
    }

    @Test
    public void findAll() {
        Member member = Member.createMember("test", "test", "sleeper", 25L, RoleType.USER);
        memberRepository.save(member);
        List<Member> members = memberRepository.findAll();
        Assertions.assertThat(members.size()).isEqualTo(2);
    }

    @Test
    public void findById() {
        Member member = memberRepository.findById("sleeper").get();
        Assertions.assertThat(member.getMemberPassword()).isEqualTo("sleeper12@@");
    }
}