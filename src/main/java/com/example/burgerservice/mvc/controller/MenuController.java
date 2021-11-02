package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @GetMapping
    public String showMenu() {
        return "menu";
    }
}
