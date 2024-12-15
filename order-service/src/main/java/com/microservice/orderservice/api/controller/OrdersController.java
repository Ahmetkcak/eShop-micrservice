package com.microservice.orderservice.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.microservice.orderservice.dto.request.PlaceOrderRequest;
import com.microservice.orderservice.dto.response.GetAllOrderResponse;
import com.microservice.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody PlaceOrderRequest placeOrderRequest) {
        orderService.placeOrder(placeOrderRequest);
        return "Order placed successfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllOrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }
}
