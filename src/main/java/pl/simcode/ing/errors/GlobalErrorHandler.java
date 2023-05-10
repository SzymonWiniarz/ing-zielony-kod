package pl.simcode.ing.errors;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.badRequest().body(new ErrorDto(e.getMessage()));
    }

}
