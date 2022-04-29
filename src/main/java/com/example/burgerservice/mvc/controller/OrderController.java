package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.domain.OrderStatus;
import com.example.burgerservice.mvc.service.OrderService;
import com.example.burgerservice.rest.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;

@Controller
@RequestMapping("/orders")
@SessionAttributes("burgerOrder")
public class OrderController {

    private final OrderService orderService;
    private final OrderStatusService orderStatusService;

    @Autowired
    public OrderController(OrderService orderService, OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
        this.orderService = orderService;
    }


    @GetMapping("/current")
    public String getOrderForm(Model model) {
        model.addAttribute("order", new BurgerOrder());
        return "orderForm";
    }

    @PostMapping("/current")
    public String saveOrder(@Valid BurgerOrder burgerOrder, Model model, SessionStatus sessionStatus) {
        System.out.println(burgerOrder);
        burgerOrder.setStatus(orderStatusService.get("CREATED"));
        orderService.saveOrder(burgerOrder);
        sessionStatus.setComplete();
        LocalDateTime burgerCreationTime = burgerOrder.getCreatedAt();
        model.addAttribute("burgerTime", burgerCreationTime);
        return "home";
    }
    @PostConstruct
    public void addInitialStatuses() {
        OrderStatus orderStatus1 =  new OrderStatus(1L,"CREATED");
        OrderStatus orderStatus2 = new OrderStatus(2L,"IN_PROGRESS");
        OrderStatus orderStatus3 = new OrderStatus(3L, "DONE");
        OrderStatus orderStatus4 = new OrderStatus(4L, "CANCELED");
        orderStatusService.saveAll(Arrays.asList(orderStatus1, orderStatus2, orderStatus3, orderStatus4));
    }

}
