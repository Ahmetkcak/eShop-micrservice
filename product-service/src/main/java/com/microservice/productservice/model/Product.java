package com.microservice.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    private String id;
    private String name;
    private String description;
    private double price;
}