package com.example.crm.exception.base;

import com.example.crm.core.utilities.results.ErrorResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResponseEntity<ErrorResult> handleValidationException(Exception e) {
        ValidationException exception = (ValidationException) e;
        logger.error("[ValidationException]. Exception: {0}", exception);
        return ResponseEntity.status(400).body(new ErrorResult(exception.getMessage()));
    }
}
