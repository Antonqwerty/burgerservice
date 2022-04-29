package com.example.burgerservice.rest.service.impl;

import com.example.burgerservice.constant.CacheConstants;
import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.repository.BurgerOrderRepository;
import com.example.burgerservice.rest.dto.BurgerOrderDto;
import com.example.burgerservice.rest.mapper.BurgerOrderMapper;
import com.example.burgerservice.rest.mapper.OrderStatusMapper;
import com.example.burgerservice.rest.service.OrderService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl implements OrderService {

    private final BurgerOrderRepository burgerOrderRepository;
    private final BurgerOrderMapper burgerOrderMapper;

    public OrderServiceImpl(BurgerOrderRepository burgerOrderRepository, BurgerOrderMapper burgerOrderMapper) {

        this.burgerOrderRepository = burgerOrderRepository;
        this.burgerOrderMapper = burgerOrderMapper;
    }

    @Override
    public BurgerOrderDto getOrder(Long id) {
        BurgerOrder burgerOrder = burgerOrderRepository.findById(id).get();
        return burgerOrderMapper.burgerOrderDao2Dto(burgerOrder);
    }

    @Override
    public BurgerOrderDto getLastOrder() {
        Iterable<BurgerOrder> burgerOrder = burgerOrderRepository.findAll();
        return StreamSupport.stream(burgerOrder.spliterator(), false)
                .max(Comparator.comparing(BurgerOrder::getCreatedAt))
                .map(burgerOrderMapper::burgerOrderDao2Dto)
                .orElse(null);
    }

    @Override
    @Cacheable(cacheNames = CacheConstants.BURGER_ORDERS)
    public Iterable<BurgerOrder> getAll() {
        return burgerOrderRepository.findAll();
    }

}
