package econo.app.sleeper.exception;

import econo.app.sleeper.exception.error.ErrorCode;
import io.jsonwebtoken.JwtException;
import lombok.Getter;

@Getter
public class CustomJwtException extends RuntimeException {

    public final ErrorCode errorCode;
    public CustomJwtException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
