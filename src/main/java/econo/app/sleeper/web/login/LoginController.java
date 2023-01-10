package econo.app.sleeper.web.login;

import econo.app.sleeper.domain.user.User;
import econo.app.sleeper.service.login.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "login", description = "로그인 관련 API")
public class LoginController {
    private final LoginService loginService;

    @Operation(summary = "api simple explain", description = "api specific explain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        User unidentifiedUser = loginService.login(loginRequest);

        log.info("login? {}",unidentifiedUser);

        //실패
        if(unidentifiedUser == null){
            LoginResponse loginResponse = LoginResponse.builder()
                    .message("존재하지 않는 아이디입니다.")
                    .build();
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<>(loginResponse,HttpStatus.BAD_REQUEST);
        }

        //성공
        LoginResponse loginResponse = LoginResponse.builder()
                .message("로그인 성공")
                // JWT TOKEN 추가
                .build();
        return new ResponseEntity<>(loginResponse,HttpStatus.OK);
    }

}
