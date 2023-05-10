package econo.app.sleeper.service.auth;

import econo.app.sleeper.domain.Auth.Auth;
import econo.app.sleeper.domain.Auth.AuthRepository;
import econo.app.sleeper.domain.member.Member;
import econo.app.sleeper.domain.member.MemberRepository;
import econo.app.sleeper.domain.member.RoleType;
import econo.app.sleeper.web.login.JwtTokenProvider;
import econo.app.sleeper.web.login.LoginRequest;
import econo.app.sleeper.web.login.LoginTokenDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private AuthRepository authRepository;
    @InjectMocks
    @Spy
    private AuthService authService;

    private LoginRequest loginRequest;

    private LoginTokenDto loginTokenDto;

    private Member member;
    private Auth auth;

    @BeforeEach
    public void setUp(){
        loginRequest = new LoginRequest("eotjs","123@@");
        loginTokenDto = new LoginTokenDto("accessToken","refreshToken");
        auth = new Auth("accessToken","refreshToken");
        member = new Member("eotjs","123@@","소니",25L, RoleType.ADMIN);
    }

    @DisplayName("아이디 불일치")
    @Test
    void login_throws_IllegalArgumentException_id() {
        when(memberRepository.findById(loginRequest.getMemberId())).thenReturn(Optional.empty());
        Assertions.assertThatThrownBy(() -> authService.login(loginRequest)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("패스워드 불일치")
    @Test
    void login_throws_IllegalArgumentException_pw() {
        when(memberRepository.findById(loginRequest.getMemberId())).thenReturn(Optional.ofNullable(member));
        Assertions.assertThatThrownBy(() -> authService.login(loginRequest)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("login시 loginTokenDto가 반환되는 지 확인")
    @Test
    void login_return_loginTokenDto(){
        doReturn(Optional.of(member)).when(memberRepository).findById(loginRequest.getMemberId());
        doReturn(loginTokenDto).when(authService).generateToken(loginRequest.getMemberId());
        Assertions.assertThat(authService.login(loginRequest)).isEqualTo(loginTokenDto);
    }

    @Test
    void generateToken() {
        when(jwtTokenProvider.createAccessToken(loginRequest.getMemberId())).thenReturn("accessToken");
        when(jwtTokenProvider.createRefreshToken(loginRequest.getMemberId())).thenReturn("refreshToken");
        Assertions.assertThat(authService.generateToken(loginRequest.getMemberId())).isEqualTo(loginTokenDto);
    }

    @Test
    void logout() {

    }
}