package com.example.burgerservice.rest.service.impl;

import com.example.burgerservice.constant.CacheConstants;
import com.example.burgerservice.mvc.domain.OrderStatus;
import com.example.burgerservice.mvc.repository.OrderStatusRepository;
import com.example.burgerservice.rest.service.OrderStatusService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public OrderStatus save(OrderStatus orderStatus) {
        return orderStatusRepository.save(orderStatus);
    }

    @Override
    public Iterable<OrderStatus> saveAll(List<OrderStatus> orderStatuses) {
        return orderStatusRepository.saveAll(orderStatuses);
    }

    @Override
    @Cacheable(cacheNames = CacheConstants.ORDER_STATUS, key = "#status")
    public OrderStatus get(String status) {
        return orderStatusRepository.findByStatusEquals(status);
    }

}
