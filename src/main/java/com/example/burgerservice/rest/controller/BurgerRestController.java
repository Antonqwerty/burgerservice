package com.example.burgerservice.rest.controller;

import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.rest.dto.BurgerDto;
import com.example.burgerservice.rest.service.BurgerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/api/burger")
public class BurgerRestController {
    private final BurgerService burgerService;

    public BurgerRestController(BurgerService burgerService) {
        this.burgerService = burgerService;
    }

    @GetMapping("/{id}")
    public BurgerDto getBurger(@PathVariable("id") Long id) {
        return burgerService.getBurger(id);
    }

    @PostMapping
    public BurgerDto saveBurger(@RequestBody BurgerDto burgerDto) {
        return burgerService.saveBurger(burgerDto);
    }
}
