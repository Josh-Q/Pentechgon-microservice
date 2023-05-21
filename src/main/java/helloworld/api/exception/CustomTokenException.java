package helloworld.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
public class CustomTokenException extends RuntimeException {

    private static final long serialVersionUID = 8683755066007599812L;
    private final HttpStatus httpStatus;
    private final String message;

}
