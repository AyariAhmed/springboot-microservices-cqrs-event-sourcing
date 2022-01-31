package com.cqrs.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "products")
public class ProductEntity {
    @Id
    @Field(type = FieldType.Keyword)
    private String ref;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Text, name = "desc")
    private String description;

    @Field(type = FieldType.Float, name = "price")
    private float price;

    @Field(type = FieldType.Integer, name = "quantity")
    private int quantity;
}