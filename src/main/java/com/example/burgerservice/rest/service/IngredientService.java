package com.example.burgerservice.rest.service;

import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.rest.dto.IngredientDto;

public interface IngredientService {
    IngredientDto getIngredient(String id);
}
