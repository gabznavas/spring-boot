package io.github.gabznavas.btgpactual.orderms.listener;

import io.github.gabznavas.btgpactual.orderms.config.RabbitMqConfig;
import io.github.gabznavas.btgpactual.orderms.dto.OrderCreatedEvent;
import io.github.gabznavas.btgpactual.orderms.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = RabbitMqConfig.ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message) {
        /*
            payload do message = OrderCreatedEvent
        * {
               "codigoPedido":1001,
               "codigoCliente":1,
               "itens":[
                  {
                     "produto":"lápis",
                     "quantidade":100,
                     "preco":1.10
                  }
               ]
            }
        * */
        logger.info("message consumed: {}", message);
        orderService.save(message.getPayload());
    }
}
