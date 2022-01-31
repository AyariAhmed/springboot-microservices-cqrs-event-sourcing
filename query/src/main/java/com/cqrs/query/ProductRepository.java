package com.cqrs.query;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<ProductEntity, String> {
    @NonNull
    List<ProductEntity> findAll();
}
