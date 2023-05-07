package econo.app.sleeper.web.login;

import econo.app.sleeper.domain.member.Member;
import econo.app.sleeper.domain.member.MemberRepository;
import econo.app.sleeper.service.auth.AuthService;
import econo.app.sleeper.web.common.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "login", description = "로그인 관련 API")
public class LoginController {

    private final AuthService authService;
    private final MemberRepository memberRepository;

    @Operation(summary = "api simple explain", description = "api specific explain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest,HttpServletResponse response) {
        LoginTokenDto loginTokenDto = authService.login(loginRequest);

        String memberId= loginRequest.getMemberId();
        Member member = memberRepository.findById(memberId).get();
        Long memberPk= member.getId();
        Long characterPk = member.getCharacter().getId();
        Long sleepAdvisorPk = member.getSleepAdvisor().getId();

        response.addHeader("authorization", loginTokenDto.getAccessToken());
        response.addHeader("refreshToken", loginTokenDto.getRefreshToken());

        LoginResponse loginResponse = new LoginResponse(characterPk,memberPk,sleepAdvisorPk,LoginMessage.issue_AccessToken_RefreshToken.toString());
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }


    @PostMapping("/auth/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = WebUtils.getCookie(request, "refreshToken");
        Cookie nullCookie = authService.logout(cookie);
        response.addCookie(nullCookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/auth/re-request")
    public ResponseEntity<CommonResponse> reRequest(){
        CommonResponse commonResponse = new CommonResponse(LoginMessage.re_request_with_AccessToken_RefreshToken.toString());
        return new ResponseEntity<>(commonResponse,HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/auth/reissue")
    public ResponseEntity<Void> reissue(String refreshToken, String accessToken, String loginId,HttpServletResponse response){
        if(authService.isEqualToRefreshToken(accessToken,refreshToken)){
            LoginTokenDto loginTokenDto = authService.generateToken(loginId);
            response.addHeader("authorization", loginTokenDto.getAccessToken());
            response.addHeader("refreshToken", loginTokenDto.getRefreshToken());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/auth/fail")
    public ResponseEntity<Void> loginFail(){
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }


}
