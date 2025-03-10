package io.github.gabznavas.Book.API.exceptions;

import java.util.Date;

public record ExceptionResponse(
        Date timestamp,
        String message,
        String details
) {}
