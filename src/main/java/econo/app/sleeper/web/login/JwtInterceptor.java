package econo.app.sleeper.web.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;

    public JwtInterceptor(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String accessToken = jwtTokenProvider.extractAccessToken(request);
        String refreshToken = jwtTokenProvider.extractRefreshToken(request);

        if (jwtTokenProvider.isValidAccessToken(accessToken)) {
            return true;
        }

        if (refreshToken.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/auth/re-request");
            return false;
        }
        if (jwtTokenProvider.isValidRefreshToken(refreshToken)) {
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/auth/fail");
        return false;
    }
}