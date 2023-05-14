package econo.app.sleeper.web.login;

import econo.app.sleeper.exception.CustomJwtException;
import econo.app.sleeper.exception.error.JwtErrorCode;
import io.jsonwebtoken.*;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;


@Component
@Slf4j
@NoArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.accessSecretKey}")
    private  String accessSecretKey;

    @Value("${jwt.refreshSecretKey}")
    private String refreshSecretKey;

    @Value("${jwt.accessTokenExp}")
    private long accessTokenExp;
    // 10분

    @Value("${jwt.refreshTokenExp}")
    private long refreshTokenExp;
    // 10일

    public String createAccessToken(String userId) {
        Date validity = new Date(System.currentTimeMillis()
                + accessTokenExp);
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, accessSecretKey)
                .compact();
    }


    public String createRefreshToken(String userId) {
        Date validity = new Date(System.currentTimeMillis()
                + refreshTokenExp);
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, refreshSecretKey)
                .compact();
    }

    public String extractAccessToken(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization").replace("Bearer ","");
        return accessToken;
    }
    public Optional<String> extractRefreshToken(HttpServletRequest request) {
        if(request.getHeader("refreshToken") == null){
            return Optional.ofNullable(null);
        }
        String refreshToken = request.getHeader("refreshToken").replace("Bearer ","");
        return Optional.ofNullable(refreshToken);
    }

    public boolean isValidAccessToken(String accessToken) {
        try {
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(accessSecretKey).build();
            jwtParser.parseClaimsJws(accessToken);
            return true;
        } catch (MalformedJwtException e) {
            throw new CustomJwtException(JwtErrorCode.Invalid_JWT);
        } catch (UnsupportedJwtException e) {
            throw new CustomJwtException(JwtErrorCode.Unsupported_JWT);
        } catch (SignatureException e) {
            throw new CustomJwtException(JwtErrorCode.Invalid_JWT_Signature);
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    public boolean isValidRefreshToken(String refreshToken){
        try{
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(refreshSecretKey).build();
            jwtParser.parseClaimsJws(refreshToken);
            return true;
        }catch (MalformedJwtException e){
            throw new CustomJwtException(JwtErrorCode.Invalid_JWT);
        }catch (UnsupportedJwtException e){
            throw new CustomJwtException(JwtErrorCode.Unsupported_JWT);
        }catch (SignatureException e){
            throw new CustomJwtException(JwtErrorCode.Invalid_JWT_Signature);
        }catch (ExpiredJwtException e) {
            return false;
        }

    }

}