package com.example.burgerservice.rest.service;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.rest.dto.BurgerOrderDto;

public interface OrderService {
    BurgerOrderDto getOrder(Long id);

    BurgerOrderDto getLastOrder();

    Iterable<BurgerOrder> getAll();
}
