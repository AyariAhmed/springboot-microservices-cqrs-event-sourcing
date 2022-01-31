package com.cqrs.query.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreatedEvent {
    private String ref;
    private String name;
    private String description;
    private float price;
    private int quantity;
}
