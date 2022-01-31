package com.cqrs.command;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Document(collection = "Products")
public class ProductEntity {
    @Id
    private String ref;
    private String name;
    private String description;
    private float price;
    private int quantity;
}