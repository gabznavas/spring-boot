package io.github.gabznavas.picpay.client;

import io.github.gabznavas.picpay.entity.Transfer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        url = "${client.notification-service.url}",
        name = "NotificationClient"
)
public interface NotificationClient {
    @PostMapping
    ResponseEntity<Void> sendNotification(@RequestBody Transfer transfer);
}
