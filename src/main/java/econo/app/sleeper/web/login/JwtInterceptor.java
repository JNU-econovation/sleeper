package econo.app.sleeper.web.login;

import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class JwtInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;

    public JwtInterceptor(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
    //검증만 한다
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = jwtTokenProvider.extractAccessToken(request);
        Claims accessClaims = jwtTokenProvider.getClaimsAccessToken(accessToken);
//        String userId = accessClaims.getId();
        if (accessToken.isEmpty()) {
//            String newAccessToken = jwtTokenProvider.createAccessToken(userId);
//            String newRefreshToken = jwtTokenProvider.createRefreshToken(userId);
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        if (jwtTokenProvider.isValidAccessToken(accessToken))
        {
//            String newAccessToken = jwtTokenProvider.createAccessToken(userId);

            if (accessClaims.getExpiration().before(new Date())) {
                String refreshToken = jwtTokenProvider.extractRefreshToken(request);
//            Claims refreshClaims = jwtTokenProvider.getClaimsRefreshToken(refreshToken);
                if (jwtTokenProvider.isValidRefreshToken(refreshToken)) {
//                String newAccessToken = jwtTokenProvider.createAccessToken(userId);
                    return true;
                }
                else{response.sendRedirect(request.getContextPath() + "/"); return false;}
            }}
        if (!jwtTokenProvider.isValidAccessToken(accessToken)){
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);

    }

}