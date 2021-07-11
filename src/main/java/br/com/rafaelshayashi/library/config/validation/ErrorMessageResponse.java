package br.com.rafaelshayashi.library.config.validation;

import java.util.List;

public class ErrorMessageResponse {

    private final String message;
    private final List<FieldErrorResponse> errors;

    public ErrorMessageResponse(String message, List<FieldErrorResponse> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public List<FieldErrorResponse> getErrors() {
        return errors;
    }

}
