package io.github.gabznavas.picpay.client;

import io.github.gabznavas.picpay.client.dto.AuthorizationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        url = "${client.authorization-service.url}",
        name = "AuthorizationClient"
)
public interface AuthorizationClient {
    @GetMapping
    ResponseEntity<AuthorizationResponse> isAuthorized();
}
