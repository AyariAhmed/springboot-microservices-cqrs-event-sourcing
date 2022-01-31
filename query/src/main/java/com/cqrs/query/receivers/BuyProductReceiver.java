package com.cqrs.query.receivers;

import com.cqrs.query.ProductEntity;
import com.cqrs.query.ProductService;
import com.cqrs.query.events.ProductBoughtEvent;
import org.springframework.messaging.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BuyProductReceiver {
    ProductService productService;

    public BuyProductReceiver(ProductService productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = "buy-product-queue")
    public void receiveMessage(Message<ProductBoughtEvent> message) {
        ProductEntity product = this.productService.getProductByRef(message.getPayload().getRef());
        if (product != null) {
            product.setQuantity(product.getQuantity() - 1);
            productService.addProduct(product);
        }
    }
}
