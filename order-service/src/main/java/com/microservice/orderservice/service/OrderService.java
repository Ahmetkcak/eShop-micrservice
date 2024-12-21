package com.microservice.orderservice.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.microservice.orderservice.dto.response.GetAllOrderResponse;

import org.springframework.stereotype.Service;

import com.microservice.orderservice.client.InventoryClient;
import com.microservice.orderservice.dto.request.PlaceOrderRequest;
import com.microservice.orderservice.model.Order;
import com.microservice.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(PlaceOrderRequest placeOrderRequest) {
        
        boolean isProductInStock = inventoryClient.isInStock(placeOrderRequest.skuCode(), placeOrderRequest.quantity());
        if (isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setSkuCode(placeOrderRequest.skuCode());
            order.setPrice(placeOrderRequest.price());
            order.setQuantity(placeOrderRequest.quantity());
            orderRepository.save(order);
        }else{
            throw new RuntimeException("Product is out of stock");
        }

       
    }

    public List<GetAllOrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> new GetAllOrderResponse(order.getId(), order.getOrderNumber(), order.getSkuCode(), order.getPrice(), order.getQuantity()))
                .collect(Collectors.toList());
    }
}
