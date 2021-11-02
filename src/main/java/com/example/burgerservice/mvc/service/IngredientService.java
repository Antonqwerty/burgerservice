package com.example.burgerservice.mvc.service;

import com.example.burgerservice.mvc.domain.Ingredient;

import java.util.List;

public interface IngredientService {
    void saveAllIngredients(List<Ingredient> ingredients);

    Iterable<Ingredient> getAllIngredients();
}
