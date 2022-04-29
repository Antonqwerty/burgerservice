package com.example.burgerservice.rest.controller;

import com.example.burgerservice.rest.dto.BurgerOrderDto;
import com.example.burgerservice.rest.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<BurgerOrderDto> getOrder(@PathVariable(name = "orderId") Long orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @GetMapping("/last")
    public BurgerOrderDto getLatestOrder() {
        return orderService.getLastOrder();
    }


}
