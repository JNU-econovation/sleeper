package econo.app.sleeper.util.error;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String getName();
    HttpStatus getHttpStatus();
    String getMessage();
}
