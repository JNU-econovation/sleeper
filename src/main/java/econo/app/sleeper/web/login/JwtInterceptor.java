package econo.app.sleeper.web.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String accessToken = jwtTokenProvider.extractAccessToken(request);

        if (jwtTokenProvider.isValidAccessToken(accessToken)) {
            return true;
        }else{
            Optional<String> refreshToken = jwtTokenProvider.extractRefreshToken(request);
            if (refreshToken.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/auth/re-request");
                return false;
            }else{
                if (jwtTokenProvider.isValidRefreshToken(refreshToken.get())) {
                    return true; // 토큰들 재발행
                }else{
                    response.sendRedirect(request.getContextPath() + "/auth/fail");
                    return false;
                }
            }
        }
    }
}