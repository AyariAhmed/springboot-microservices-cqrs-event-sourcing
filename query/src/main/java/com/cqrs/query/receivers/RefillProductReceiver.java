package com.cqrs.query.receivers;

import com.cqrs.query.ProductEntity;
import com.cqrs.query.ProductService;
import com.cqrs.query.events.ProductRefilledEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class RefillProductReceiver {
    ProductService productService;

    public RefillProductReceiver(ProductService productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = "refill-product-queue")
    public void receiveMessage(Message<ProductRefilledEvent> message) {
        ProductEntity product = this.productService.getProductByRef(message.getPayload().getRef());
        if (product != null) {
            product.setQuantity(product.getQuantity() + message.getPayload().getQuantity());
            productService.addProduct(product);
        }
    }
}