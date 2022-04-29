package com.example.burgerservice.mvc.service.impl;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.repository.BurgerOrderRepository;
import com.example.burgerservice.mvc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceMvcImpl implements OrderService {
    private final BurgerOrderRepository burgerOrderRepository;

    @Autowired
    public OrderServiceMvcImpl(BurgerOrderRepository burgerOrderRepository) {
        this.burgerOrderRepository = burgerOrderRepository;
    }

    @Override
    public BurgerOrder saveOrder(BurgerOrder burgerOrder) {
        return burgerOrderRepository.save(burgerOrder);
    }

}
