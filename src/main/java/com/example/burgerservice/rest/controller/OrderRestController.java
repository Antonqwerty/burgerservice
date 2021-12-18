package com.example.burgerservice.rest.controller;

import com.example.burgerservice.mvc.domain.OrderStatus;
import com.example.burgerservice.mvc.repository.OrderStatusRepository;
import com.example.burgerservice.rest.dto.BurgerOrderDto;
import com.example.burgerservice.rest.dto.OrderStatusDto;
import com.example.burgerservice.rest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {
    private final OrderService orderService;
    private final OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderRestController(OrderService orderService, OrderStatusRepository orderStatusRepository) {
        this.orderService = orderService;
        this.orderStatusRepository = orderStatusRepository;
    }

    @GetMapping("/{orderId}")
    //responseEntity - обертка, которая позволяет кастомизировать ответ.
    public ResponseEntity<BurgerOrderDto> getOrder(@PathVariable(name = "orderId") Long orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
        //возвращаем 200 (ок) и говорим верни заказ
    }

    @GetMapping("/last")
    public BurgerOrderDto getLatestOrder() {
        return orderService.getLastOrder();
    }

    @PostConstruct
    public void addInitialStatuses() {
        OrderStatus orderStatus1 = new OrderStatus(1L,"CREATED");
        OrderStatus orderStatus2 = new OrderStatus(2L,"IN_PROGRESS");
        OrderStatus orderStatus3 = new OrderStatus(3L, "DONE");
        OrderStatus orderStatus4 = new OrderStatus(4L, "CANCELED");
        orderStatusRepository.saveAll(Arrays.asList(orderStatus1, orderStatus2, orderStatus3, orderStatus4));
    }
}
