package io.github.gabznavas.math.exceptions;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {}
