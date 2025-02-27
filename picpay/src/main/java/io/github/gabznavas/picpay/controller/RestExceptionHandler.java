package io.github.gabznavas.picpay.controller;

import io.github.gabznavas.picpay.execption.PicPayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PicPayException.class)
    public ProblemDetail handlePicPayException(PicPayException e) {
        return e.toProblemDetail();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        var fieldErrors = e.getFieldErrors().stream().map(fieldError -> new InvalidParam(fieldError.getField(), fieldError.getDefaultMessage())).toList();

        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Your request parameters didn't validate.");
        pb.setProperty("invalid-params", fieldErrors);
        return pb;
    }

    private record InvalidParam(String fieldName, String reason) {
    }
}
