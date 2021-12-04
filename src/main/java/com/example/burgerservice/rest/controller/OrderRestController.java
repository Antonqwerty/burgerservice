package com.example.burgerservice.rest.controller;

import com.example.burgerservice.rest.dto.BurgerOrderDto;
import com.example.burgerservice.rest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {
    private final OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    //responseEntity - обертка, которая позволяет кастомизировать ответ.
    public ResponseEntity<BurgerOrderDto> getOrder(@PathVariable(name = "orderId") Long orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    public BurgerOrderDto saveOrder() {
        return null;
    }

}
