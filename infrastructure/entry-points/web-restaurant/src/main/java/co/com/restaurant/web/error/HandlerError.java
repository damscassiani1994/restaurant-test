package co.com.restaurant.web.error;

import co.com.restaurant.model.exception.ErrorMessage;
import co.com.restaurant.model.exception.SurveyException;
import co.com.restaurant.web.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalTime;

@ControllerAdvice
public class HandlerError {

    @ExceptionHandler(SurveyException.class)
    public ResponseEntity<ErrorResponse> generalException(
            SurveyException ex, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(
                ErrorResponse
                        .builder()
                        .code(ex.getErrorMessage().getCode())
                        .message(ex.getErrorMessage().getMessage())
                        .time(LocalTime.now()).build(), new HttpHeaders(), HttpStatus.resolve(ex.getErrorMessage().getCode()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> argumentNotValidException(
            HttpMessageNotReadableException ex, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(
                ErrorResponse
                        .builder()
                        .code(ErrorMessage.BAD_REQUEST.getCode())
                        .message(ErrorMessage.BAD_REQUEST.getMessage())
                        .time(LocalTime.now()).build(), new HttpHeaders(), HttpStatus.resolve(ErrorMessage.BAD_REQUEST.getCode()));
    }
}
