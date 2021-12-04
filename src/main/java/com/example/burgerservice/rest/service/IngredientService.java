package com.example.burgerservice.rest.service;

import com.example.burgerservice.rest.dto.IngredientDto;
import com.example.burgerservice.rest.dto.IngredientListWrapperDto;

public interface IngredientService {
    IngredientDto getIngredient(String id);

    IngredientDto saveIngredient(IngredientDto ingredient);

    IngredientListWrapperDto getAllIngredients();
}
