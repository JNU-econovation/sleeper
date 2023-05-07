package econo.app.sleeper.web.login;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static io.jsonwebtoken.SignatureAlgorithm.PS256;


@Component
@Slf4j
public class JwtTokenProvider {

    private final String accessSecretKey = "sleeper_is_good_for_you";
    private final String refreshSecretKey = "bokdungi_is_a_good_team";
    private long accessTokenExp;
    private long refreshTokenExp;

    public JwtTokenProvider(@Value("600000") long accessTokenExp,@Value("3600000") long refreshTokenExp) {
        this.accessTokenExp = accessTokenExp;
        this.refreshTokenExp = refreshTokenExp;
    }

    public String createAccessToken(String userId) {
        Date validity = new Date(System.currentTimeMillis()
                + accessTokenExp);
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("alg","HS256")
                .setIssuer("sleeper")
                .setSubject(userId)
                .setExpiration(validity)
                .setIssuedAt(now)
                .signWith(HS256, accessSecretKey.getBytes())
                .compact();
    }

    public String createRefreshToken(String userId) {
        Date validity = new Date(System.currentTimeMillis()
                + refreshTokenExp);
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("alg","HS256")
                .setIssuer("sleeper")
                .setSubject(userId)
                .setExpiration(validity)
                .setIssuedAt(now)
                .signWith(HS256, refreshSecretKey.getBytes())
                .compact();
    }

    public String extractAccessToken(HttpServletRequest request) {
        String accessToken = request.getHeader("authorization");
        return accessToken;
    }
    public String extractRefreshToken(HttpServletRequest request) {
        String refreshToken = request.getHeader("refreshToken");
        return refreshToken;
    }

    public String getSignatureAccessToken(String token) {
        return Jwts.parser()
                .setSigningKey(accessSecretKey.getBytes())
                .parseClaimsJws(token)
                .getSignature();
    }

    public Claims getClaimsAccessToken(String token) {
        return Jwts.parser()
                .setSigningKey(accessSecretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    private String getSignatureRefreshToken(String token) {
        return Jwts.parser()
                .setSigningKey(refreshSecretKey.getBytes())
                .parseClaimsJws(token)
                .getSignature();
    }

    public boolean isValidAccessToken(String accessToken) {
        try{
            String signatureAccessToken = getSignatureAccessToken(accessToken);
        }catch (ExpiredJwtException e){
            return false;
        }
        return true;
    }

    public boolean isValidRefreshToken(String refreshToken){
        try{
            String signatureRefreshToken = getSignatureRefreshToken(refreshToken);
        }catch (ExpiredJwtException e){
            return false;
        }
        return true;

    }

}