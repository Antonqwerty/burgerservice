package com.example.burgerservice.mvc.service;

import com.example.burgerservice.mvc.domain.IngredientType;

import java.util.List;

public interface IngredientTypeService {
    List<IngredientType> getAllTypes();
}
