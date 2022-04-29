package com.example.burgerservice.mvc.service;

import com.example.burgerservice.mvc.domain.Ingredient;

import java.util.List;
import java.util.Map;

public interface IngredientService {
    void saveAllIngredients(List<Ingredient> ingredients);

    Iterable<Ingredient> getAllIngredients();

    Map<String, List<Ingredient>> getIngredients();
}
