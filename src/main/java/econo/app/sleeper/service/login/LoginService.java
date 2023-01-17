package econo.app.sleeper.service.login;

import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.repository.UserRepository;
import econo.app.sleeper.web.login.JwtTokenProvider;
import econo.app.sleeper.web.login.LoginRequest;
import econo.app.sleeper.web.login.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public LoginResponse login(LoginRequest loginRequest, HttpServletRequest request) {
        String loginId = loginRequest.getUserId();
        Optional<User> user = userRepository.findById(loginId);

        if (user.isEmpty()) {
            return new LoginResponse("존재하지않는 회원입니다", null, null, null);
        }

        if (!user.get().getUserPassword().equals(loginRequest.getUserPassword())) {
            return new LoginResponse("비밀번호가 일치하지 않습니다.", null, null, null);
        }
        String userId = user.get().getUserId();
        Long userPk = user.get().getUserPk();

        String newAccessToken = jwtTokenProvider.createAccessToken(userId);
        String newRefreshToken = jwtTokenProvider.createRefreshToken(userId);
        return new LoginResponse("access와 refresh토큰이 생성되었습니다", newAccessToken, newRefreshToken, userPk);
    }
    public Cookie logout(Cookie cookie) {
        cookie.setValue(null);
        cookie.setMaxAge(0);
        //cookie에서 refreshToken 추출하기
        //cookie 에 null값 줘서 login 풀기
        return cookie;
    }
    // todo r로그인 다시 해보기
    // todo c회원가입 만들어보기


    // todo u 회원정보 수정 업데이트는 고민 해봐야함
}




        /*

        if (userRepository.findById(loginRequest.getUserId()) == null) {
            LoginResponse loginResponse = LoginResponse.of("null", "null", "존재하지않는 회원입니다", null);
            return loginResponse;
        }

        Optional<User> user = userRepository.findById(loginRequest.getUserId());
        if (!loginRequest.getUserPassword().equals(user.get().getUserPassword())) {
            LoginResponse loginResponse = LoginResponse.of("null", "null", "비밀번호가 일치하지 않습니다.", null);
            return loginResponse;
        }

        String accessToken = jwtTokenProvider.extractAccessToken(request);
        String validAccessToken = jwtTokenProvider.isValidAccessToken(accessToken);
        if (accessToken.equals(null)) {
            String createdAccessToken = jwtTokenProvider.createAccessToken(user.get().getUserId());
            String createdRefreshToken = jwtTokenProvider.createRefreshToken(user.get().getUserId());
            LoginResponse loginResponse = LoginResponse.of(createdAccessToken, createdRefreshToken, "accessToken 과 refreshToken이 발급되었습니다.", user.get().getUserPk());
            Auth auth = loginResponse.toEntity(createdAccessToken, createdRefreshToken, user.get());
            authRepository.save(auth);
            return loginResponse;
        }
        if (validAccessToken.equals("A_JwtException") || validAccessToken.equals("A_NullpointerException")) {
            LoginResponse loginResponse = LoginResponse.of("null", "null", "accessToken이 잘못 되었습니다.", null);
            return loginResponse;
        }
        String refreshToken = jwtTokenProvider.extractRefreshToken(request);
        String validRefreshToken = jwtTokenProvider.isValidRefreshToken(refreshToken);
        if (validAccessToken.equals("A_ExpiredJwtException")) {
            if (!refreshToken.equals(authRepository.findByRefresh(loginRequest.getUserId()))){
                LoginResponse loginResponse = LoginResponse.of("null", "null", "access토큰 만료, refreshToken이 잘못 되었습니다.", null);
                return loginResponse;
            }
            if (validRefreshToken.equals("R_Pass")) {
                String createdAccessToken = jwtTokenProvider.createAccessToken(user.get().getUserId());
                LoginResponse loginResponse = LoginResponse.of(createdAccessToken, refreshToken, "refresh토큰 유효, accessToken 재발급되었습니다.", user.get().getUserPk());
                Auth auth = loginResponse.toEntity(createdAccessToken, refreshToken, user.get());
                authRepository.save(auth);
                return loginResponse;
            }
            if (validRefreshToken.equals("R_JwtException") || validAccessToken.equals("R_NullpointerException")) {
                LoginResponse loginResponse = LoginResponse.of("null", "null", "access토큰 만료, refreshToken이 잘못 되었습니다.", null);
                return loginResponse;
            }
            if (validRefreshToken.equals("R_ExpiredJwtException")) {
                String createdAccessToken = jwtTokenProvider.createRefreshToken(user.get().getUserId());
                String createdRefreshToken = jwtTokenProvider.createRefreshToken(user.get().getUserId());
                LoginResponse loginResponse = LoginResponse.of(createdAccessToken, createdRefreshToken, "accessToken 과 refreshToken이 발급되었습니다.", user.get().getUserPk());
                Auth auth = loginResponse.toEntity(createdAccessToken, createdRefreshToken, user.get());
                authRepository.save(auth);
                return loginResponse;
            }

        }
        */
