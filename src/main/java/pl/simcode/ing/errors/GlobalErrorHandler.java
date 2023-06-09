package pl.simcode.ing.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.simcode.ing.exceptions.ValidationException;

@ControllerAdvice
class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    ResponseEntity<ErrorDto> handleValidationException(ValidationException e) {
        return ResponseEntity.badRequest().body(new ErrorDto(e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ErrorDto> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new ErrorDto(e.getMessage()));
    }

}
