package com.example.burgerservice.mvc.service;

import com.example.burgerservice.mvc.domain.BurgerOrder;

public interface OrderService {
    BurgerOrder saveOrder(BurgerOrder burgerOrder);
}
