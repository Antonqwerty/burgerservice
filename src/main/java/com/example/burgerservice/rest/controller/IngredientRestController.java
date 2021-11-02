package com.example.burgerservice.rest.controller;

import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.rest.dto.IngredientDto;
import com.example.burgerservice.rest.service.IngredientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ingredient")
public class IngredientRestController {

    private final IngredientService ingredientService;

    public IngredientRestController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{ingredientId}")
    public IngredientDto getIngredient(@PathVariable(name = "ingredientId") String id) {
        return ingredientService.getIngredient(id);
    }
}
