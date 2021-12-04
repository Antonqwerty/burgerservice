package com.example.burgerservice.rest.service;

import com.example.burgerservice.rest.dto.IngredientTypeDto;

public interface IngredientTypeService {
    IngredientTypeDto getIngredientType(String id);

    IngredientTypeDto saveIngredientType(IngredientTypeDto ingredientTypeDto);
}
