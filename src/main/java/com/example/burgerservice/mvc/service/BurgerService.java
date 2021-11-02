package com.example.burgerservice.mvc.service;

import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.mvc.domain.Ingredient;

import java.util.List;

public interface BurgerService {
    void saveBurgerById(Long id);

    void deleteBurgerById(Long id);

    Iterable<Burger> getAllBurgers();

    Iterable<Burger> saveAllBurgers(Iterable<Burger> burgers);

    List<Burger> findBurgersByIngredientsIn(List<Ingredient> ingredients);
}
