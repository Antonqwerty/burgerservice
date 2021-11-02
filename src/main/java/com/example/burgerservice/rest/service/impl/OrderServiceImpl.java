package com.example.burgerservice.rest.service.impl;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.repository.BurgerOrderRepository;
import com.example.burgerservice.rest.dto.BurgerOrderDto;
import com.example.burgerservice.rest.mapper.BurgerOrderMapper;
import com.example.burgerservice.rest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("orderServiceRest")
public class OrderServiceImpl implements OrderService {

    private final BurgerOrderRepository burgerOrderRepository;
    private final BurgerOrderMapper burgerOrderMapper;

    @Autowired
    public OrderServiceImpl(BurgerOrderRepository burgerOrderRepository, BurgerOrderMapper burgerOrderMapper) {
        this.burgerOrderRepository = burgerOrderRepository;
        this.burgerOrderMapper = burgerOrderMapper;
    }

    @Override
    public BurgerOrderDto getOrder(Long id) {
        BurgerOrder burgerOrder = burgerOrderRepository.findById(id).get();
        return burgerOrderMapper.burgerOrderDao2Dto(burgerOrder);
    }
}
