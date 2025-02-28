package io.github.gabznavas.picpay.service;

import io.github.gabznavas.picpay.client.AuthorizationClient;
import io.github.gabznavas.picpay.client.dto.AuthorizationResponse;
import io.github.gabznavas.picpay.entity.Transfer;
import io.github.gabznavas.picpay.exception.PicPayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @Autowired
    private AuthorizationClient authorizationClient;


    public boolean isAuthorized(Transfer transfer) {
        ResponseEntity<AuthorizationResponse> response = authorizationClient.isAuthorized();
        if (response.getStatusCode().isError()) {
            throw new PicPayException();
        }

        return response.getBody().authorized();
    }
}
