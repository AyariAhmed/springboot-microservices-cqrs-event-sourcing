package com.cqrs.command.rabbitMQSenders;

import com.cqrs.command.events.ProductBoughtEvent;
import com.cqrs.command.queues.BuyProductQueue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class BuyProductSender {
    static final String ROUTING_KEY = "buy.product.";

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    public BuyProductSender(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(ProductBoughtEvent productBoughtEvent) throws JsonProcessingException {
        String productBoughtEventJson = this.objectMapper.writeValueAsString(productBoughtEvent);
        Message message = MessageBuilder
                .withBody(productBoughtEventJson.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();
        this.rabbitTemplate.convertAndSend(
                BuyProductQueue.QUEUE_NAME,
                ROUTING_KEY + productBoughtEvent.getRef(),
                message
        );
    }
}