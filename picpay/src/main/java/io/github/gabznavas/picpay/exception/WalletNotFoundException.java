package io.github.gabznavas.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletNotFoundException extends PicPayException {

    private Long walletId;

    public WalletNotFoundException(Long walletId) {
        this.walletId = walletId;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setDetail("Wallet not found");
        pb.setDetail(String.format("There is not wallet with id %s.", walletId));
        return pb;
    }
}
