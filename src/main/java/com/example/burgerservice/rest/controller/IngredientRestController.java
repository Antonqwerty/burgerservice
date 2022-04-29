package com.example.burgerservice.rest.controller;

import com.example.burgerservice.rest.dto.IngredientDto;
import com.example.burgerservice.rest.dto.IngredientListWrapperDto;
import com.example.burgerservice.rest.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/ingredient")
public class IngredientRestController {

    private final IngredientService ingredientService;

    public IngredientRestController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping(value = "/{ingredientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public IngredientDto getIngredient(@PathVariable(name = "ingredientId") String id) {
        return ingredientService.getIngredient(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public IngredientDto saveIngredient(@RequestBody IngredientDto ingredient) {
        return ingredientService.saveIngredient(ingredient);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public IngredientListWrapperDto getAllIngredients() {
        return ingredientService.getAllIngredients();
    }
}
