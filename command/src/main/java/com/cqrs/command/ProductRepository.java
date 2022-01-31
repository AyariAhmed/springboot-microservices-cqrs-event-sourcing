package com.cqrs.command;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {
    ProductEntity findByRef(String ref);
}