package econo.app.sleeper.exception;

import econo.app.sleeper.exception.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException{

    public final ErrorCode errorCode;

}
