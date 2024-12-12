package com.microservice.productservice.dto.requests;

public record CreateProductRequest(String id, String name, String description, Double price) { }
