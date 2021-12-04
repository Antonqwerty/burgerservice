package com.example.burgerservice.rest.controller;

import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.rest.dto.IngredientDto;
import com.example.burgerservice.rest.dto.IngredientListWrapperDto;
import com.example.burgerservice.rest.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/ingredient")
public class IngredientRestController {

    private final IngredientService ingredientService;

    /*    @Autowired
        public void setIngredientService(IngredientService ingredientService) {
            this.ingredientService = ingredientService;
        }

        public IngredientRestController() {

        }*/
    @Autowired
    public IngredientRestController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{ingredientId}")
    public IngredientDto getIngredient(@PathVariable(name = "ingredientId") String id) {
        log.info("getting Ingredients Type by ID");
        return ingredientService.getIngredient(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public IngredientDto saveIngredient(@RequestBody IngredientDto ingredient) {
        log.info("saving new ingredient type");
        return ingredientService.saveIngredient(ingredient);
    }

    @GetMapping("/all")
    public IngredientListWrapperDto getAllIngredients() {
        log.info("getting all ingredients");
        return ingredientService.getAllIngredients();
    }
}
