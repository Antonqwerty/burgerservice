package com.example.burgerservice.rest.controller;

import com.example.burgerservice.rest.dto.BurgerDto;
import com.example.burgerservice.rest.dto.BurgerListWrapperDto;
import com.example.burgerservice.rest.service.BurgerService;
import org.springframework.web.bind.annotation.*;

@RestController
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

    @GetMapping("/all")
    public BurgerListWrapperDto getAllBurgers() {
        BurgerListWrapperDto burgerListWrapperDto = new BurgerListWrapperDto();
        burgerListWrapperDto.setBurgers(burgerService.findAll());
        return burgerListWrapperDto;
    }

    @PutMapping
    public BurgerDto updateBurger(@RequestBody BurgerDto burgerDto) {
        return burgerService.updateBurger(burgerDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBurger(@PathVariable("id") Long id) {
        burgerService.deleteBurger(id);
    }
}
