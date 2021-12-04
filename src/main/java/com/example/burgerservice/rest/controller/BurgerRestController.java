package com.example.burgerservice.rest.controller;

import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.rest.dto.BurgerDto;
import com.example.burgerservice.rest.dto.BurgerListWrapperDto;
import com.example.burgerservice.rest.service.BurgerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/api/burger")
@Slf4j
public class BurgerRestController {
    private final BurgerService burgerService;

    public BurgerRestController(BurgerService burgerService) {
        this.burgerService = burgerService;
    }

    //в скобочках пишем параметр
    @GetMapping("/{id}")
    public BurgerDto getBurger(@PathVariable("id") Long id) {
        return burgerService.getBurger(id);
    }

    @PostMapping
    public BurgerDto saveBurger(@RequestBody BurgerDto burgerDto) {
        return burgerService.saveBurger(burgerDto);
    }
    //просто указываем endpoind без скобок
    @GetMapping("/all")
    public BurgerListWrapperDto getAllBurgers() {
        BurgerListWrapperDto burgerListWrapperDto = new BurgerListWrapperDto();
        burgerListWrapperDto.setBurgers(burgerService.findAll());
        log.info("get request");
        return burgerListWrapperDto;
    }
}
