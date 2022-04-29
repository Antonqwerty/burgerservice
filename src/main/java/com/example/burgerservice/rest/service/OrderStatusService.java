package com.example.burgerservice.rest.service;

import com.example.burgerservice.mvc.domain.OrderStatus;

import java.util.List;

public interface OrderStatusService {
    OrderStatus save(OrderStatus orderStatus);

    Iterable<OrderStatus> saveAll(List<OrderStatus> orderStatuses);

    OrderStatus get(String status);
}
