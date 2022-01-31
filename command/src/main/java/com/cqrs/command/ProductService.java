package com.cqrs.command;

import com.cqrs.command.events.ProductBoughtEvent;
import com.cqrs.command.events.ProductCreatedEvent;
import com.cqrs.command.events.ProductRefilledEvent;
import com.cqrs.command.rabbitMQSenders.BuyProductSender;
import com.cqrs.command.rabbitMQSenders.CreateProductSender;
import com.cqrs.command.rabbitMQSenders.RefillProductSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CreateProductSender createProductSender;
    private final BuyProductSender buyProductSender;
    private final RefillProductSender refillProductSender;

    public ProductService(ProductRepository productRepository, CreateProductSender createProductSender, BuyProductSender buyProductSender, RefillProductSender refillProductSender) {
        this.productRepository = productRepository;
        this.createProductSender = createProductSender;
        this.buyProductSender = buyProductSender;
        this.refillProductSender = refillProductSender;
    }

    public List<ProductEntity> getProducts() {
        return this.productRepository.findAll();
    }

    public String createProduct(ProductEntity product) throws JsonProcessingException {
        boolean isProductPresent = this.productRepository.findById(product.getRef()).isPresent();
        if (isProductPresent) {
            return "Duplicated Product ref";
        }

        ProductEntity createdProduct = this.productRepository.save(product);
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(product.getRef(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
        this.createProductSender.send(productCreatedEvent);
        return createdProduct.getName() + " is successfully created!";
    }

    public String buyProduct(String ref) throws JsonProcessingException {
        ProductEntity boughtProduct = this.productRepository.findByRef(ref);

        if (boughtProduct == null) {
            return "Product does not exist!";
        }

        if (boughtProduct.getQuantity() > 0) {
            boughtProduct.setQuantity(boughtProduct.getQuantity() - 1);
            this.productRepository.save(boughtProduct);
            ProductBoughtEvent productBoughtEvent = new ProductBoughtEvent(boughtProduct.getRef());
            this.buyProductSender.send(productBoughtEvent);
            return "Product " + boughtProduct.getName() + " bought successfully";
        }

        return "No products are available..";
    }

    public String refillProduct(String ref, int number) throws JsonProcessingException, JsonProcessingException {
        ProductEntity refilledProduct = this.productRepository.findByRef(ref);

        if (refilledProduct == null) {
            return "Product does not exist!";
        }

        if (number <= 0) {
            return "Nothing has been added";
        }

        refilledProduct.setQuantity(refilledProduct.getQuantity() + number);
        this.productRepository.save(refilledProduct);
        ProductRefilledEvent productRefilledEvent = new ProductRefilledEvent(refilledProduct.getRef(), number);
        this.refillProductSender.send(productRefilledEvent);

        return "Congrats! you refilled " + refilledProduct.getName();
    }

    public List<ProductEntity> purgeDb() {
        this.productRepository.deleteAll(this.productRepository.findAll());
        return this.productRepository.findAll();
    }
}
