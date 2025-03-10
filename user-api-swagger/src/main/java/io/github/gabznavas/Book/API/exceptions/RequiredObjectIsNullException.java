package io.github.gabznavas.Book.API.exceptions;

public class RequiredObjectIsNullException extends RuntimeException {
    public RequiredObjectIsNullException(String message) {
        super(message);
    }

    public RequiredObjectIsNullException() {
        this("It is not allowed to persist a null object");
    }
}
