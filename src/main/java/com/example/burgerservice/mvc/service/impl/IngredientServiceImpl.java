package com.example.burgerservice.mvc.service.impl;

import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.mvc.repository.IngredientRepository;
import com.example.burgerservice.mvc.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void saveAllIngredients(List<Ingredient> ingredients) {
        log.info("save all ingredients");
        ingredientRepository.saveAll(ingredients);
    }

    @Override
    public Iterable<Ingredient> getAllIngredients() {
        log.info("get all ingredients");
        return ingredientRepository.findAllIngredients();
    }
}
