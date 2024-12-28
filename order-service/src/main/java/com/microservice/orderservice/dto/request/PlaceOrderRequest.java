package com.microservice.orderservice.dto.request;

public record PlaceOrderRequest(Long id, String orderNumber,String skuCode, Double price, Integer quantity, UserDetails userDetails){
    public record UserDetails(String email, String firstName, String lastName) {}
}