package econo.app.sleeper.web.login;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.util.Date;


@Component
public class JwtTokenProvider {
    private static String secret;
    private static long tokenValidityInMilliseconds;
    private final String refreshSecret;
    private final long refreshTokenValidityInMilliseconds;

    public JwtTokenProvider(@Value("bimiliya") String secret, @Value("3600000") long tokenValidityInMilliseconds,
                            @Value("bimil") String refreshSecret, @Value("604800000") long refreshTokenValidityInMilliseconds) {
        this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds;
        this.refreshSecret = Base64.getEncoder().encodeToString(refreshSecret.getBytes());
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
    }

    //    todo userId userPk로 바꾸기
    public String createAccessToken(String userId) {
        Claims claims = Jwts.claims().setId(userId);
        Date now = new Date();
        Date validity = new Date(System.currentTimeMillis()
                + tokenValidityInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String createRefreshToken(String userId) {
        Claims claims = Jwts.claims().setId(userId);
        Date now = new Date();
        Date validity = new Date(System.currentTimeMillis()
                + refreshTokenValidityInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, refreshSecret)
                .compact();
    }

    public String extractAccessToken(HttpServletRequest request) {
        String accessToken = request.getHeader("authorization");
        return accessToken;
    }
    public String extractRefreshToken(HttpServletRequest request) {
        String refreshToken = null;
        Cookie cookie = WebUtils.getCookie(request, "refreshToken");
        if (cookie != null)
            refreshToken = cookie.getValue();
        return refreshToken;
    }

    public Claims getClaimsAccessToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                .parseClaimsJws(token)
                .getBody();
    }

    public Claims getClaimsRefreshToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(refreshSecret))
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValidAccessToken(String accessToken) {
        try {
            Claims accessClaims = getClaimsAccessToken(accessToken);
            System.out.println("Access expireTime: " + accessClaims.getExpiration());
            System.out.println("Access userId: " + accessClaims.get("userId"));
            return true;
        } catch (ExpiredJwtException exception) {
            System.out.println("토큰이 만료되었습니다. : " + exception.getClaims().get("userId"));
            return false;
        } catch (JwtException exception) {
            System.out.println("잘못된 토큰입니다.");
            return false;
        } catch (NullPointerException exception) {
            System.out.println("토큰이 비어있습니다.");
            return false;
        }

    }
    public boolean isValidRefreshToken(String refreshToken) {

        try {
            Claims accessClaims = getClaimsRefreshToken(refreshToken);
            return true;
        } catch (ExpiredJwtException exception) {
            System.out.println("토큰이 만료되었습니다. : " + exception.getClaims().get("userId"));
            return false;
        } catch (JwtException exception) {
            System.out.println("잘못된 토큰입니다.");
            return false;
        } catch (NullPointerException exception) {
            System.out.println("토큰이 비어있습니다.");
            return false;
        }

    }
}