package econo.app.sleeper.exception.error;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String getName();
    HttpStatus getHttpStatus();
    String getMessage();
}
