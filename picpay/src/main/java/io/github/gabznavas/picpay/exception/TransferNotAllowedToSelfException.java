package io.github.gabznavas.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAllowedToSelfException extends PicPayException {

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Transfer not allowed to self");
        pb.setDetail("The destination account cannot be yours.");
        return pb;
    }
}
