package com.example.burgerservice.rest.mapper;

import com.example.burgerservice.mvc.domain.OrderStatus;
import com.example.burgerservice.mvc.repository.OrderStatusRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusMapperToString {
    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusMapperToString(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    public String map(OrderStatus orderStatus) {
        return String.valueOf(orderStatus.getStatus());
    }

    public OrderStatus map(String orderStatus) {
        return orderStatusRepository.findByStatusEquals(orderStatus);
    }
}
