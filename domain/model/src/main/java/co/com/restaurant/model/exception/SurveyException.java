package co.com.restaurant.model.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SurveyException extends RuntimeException{

    private final ErrorMessage errorMessage;

    public SurveyException (Throwable cause, ErrorMessage errorMessage) {
        super(cause);
        this.errorMessage = errorMessage;
    }
}
