package com.bassem.springbootquiz.container.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bassem Al Mahow
 */

public class BadInputException extends RuntimeException {
    private List<FieldError> errors = new ArrayList();

    public BadInputException() {
    }

    public BadInputException addError(String path, String message) {
        this.errors.add(new FieldError(path, message));
        return this;
    }

    public List<FieldError> getErrors() {
        return this.errors;
    }

    public String toString() {
        return "BadInputException(errors=" + this.getErrors() + ")";
    }

    public static class FieldError {
        public String field;
        public String message;

        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String toString() {
            return "BadInputException.FieldError(field=" + this.field + ", message=" + this.message + ")";
        }
    }
}
