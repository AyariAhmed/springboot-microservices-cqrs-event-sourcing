package com.cqrs.query.receivers;

import com.cqrs.query.ProductEntity;
import com.cqrs.query.ProductService;
import com.cqrs.query.events.ProductCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class CreateProductReceiver {
    ProductService productService;

    public CreateProductReceiver(ProductService productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = "create-product-queue")
    public void receiveMessage(Message<ProductCreatedEvent> message) {
        ProductEntity product = new ProductEntity(
                message.getPayload().getRef(),
                message.getPayload().getName(),
                message.getPayload().getDescription(),
                message.getPayload().getPrice(),
                message.getPayload().getQuantity()
        );
        productService.addProduct(product);
    }
}
