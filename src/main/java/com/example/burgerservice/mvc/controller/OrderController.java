package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.service.BurgerService;
import com.example.burgerservice.mvc.service.OrderService;
import com.example.burgerservice.mvc.service.impl.BurgerServiceImpl;
import com.example.burgerservice.mvc.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/orders")
@SessionAttributes("burgerOrder")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
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
        orderService.saveOrder(burgerOrder);
        sessionStatus.setComplete();
        LocalDateTime burgerCreationTime = burgerOrder.getCreatedAt();
        model.addAttribute("burgerTime", burgerCreationTime);
        return "home";
    }

}
