package com.example.burgerservice.rest.mapper;

import com.example.burgerservice.mvc.domain.IngredientType;
import com.example.burgerservice.mvc.repository.IngredientTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IngredientTypeMapper {
    private final IngredientTypeRepository ingredientTypeRepository;

    @Autowired
    public IngredientTypeMapper(IngredientTypeRepository ingredientTypeRepository) {
        this.ingredientTypeRepository = ingredientTypeRepository;
    }

    public String map(IngredientType ingredientType) {
        return ingredientType.getId();
    }

    public IngredientType map(String ingredientType) {
        return ingredientTypeRepository.findById(ingredientType).orElseThrow();
    }
}
