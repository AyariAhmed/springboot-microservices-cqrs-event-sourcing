package com.cqrs.query.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRefilledEvent {
    private String ref;
    private int quantity;
}
