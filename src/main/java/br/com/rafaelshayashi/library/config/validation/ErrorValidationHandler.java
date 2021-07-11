package br.com.rafaelshayashi.library.config.validation;

import br.com.rafaelshayashi.library.exception.ResourceNotExistsException;
import feign.RetryableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class ErrorValidationHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RetryableException.class)
    public ErrorMessageResponse handleRetryableException(RetryableException exception) {
        return new ErrorMessageResponse("Erro connection catalogue service", Collections.emptyList());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResourceNotExistsException.class)
    public ErrorMessageResponse handleResourceNotExistsException(ResourceNotExistsException exception) {
        return new ErrorMessageResponse(
                "The resource does not exists",
                Collections.singletonList(new FieldErrorResponse("resource", exception.getResourceUuid())));
    }
}
