package econo.app.sleeper.exception.error;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode{

    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not exists"),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Parameter is invalid")
    ;

    private final HttpStatus httpStatus;
    private final String message;


    @Override
    public String getName() {
        return name();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
