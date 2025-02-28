package io.github.gabznavas.picpay.service;

import io.github.gabznavas.picpay.client.NotificationClient;
import io.github.gabznavas.picpay.entity.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private NotificationClient notificationClient;

    public void sendNotification(Transfer transfer) {
        try {
            logger.info("Send notification");
            var response = notificationClient.sendNotification(transfer);
            if (response.getStatusCode().isError()) {
                logger.info("Error while sending notification, status code is not OK");
            }
        } catch (Exception ex) {
            logger.error("Error while sending notification", ex);
        }
    }
}
