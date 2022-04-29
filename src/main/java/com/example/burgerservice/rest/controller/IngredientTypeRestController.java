package com.example.burgerservice.rest.controller;

import com.example.burgerservice.rest.dto.IngredientTypeDto;
import com.example.burgerservice.rest.service.IngredientTypeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ingredientType")
public class IngredientTypeRestController {
    public final IngredientTypeService ingredientTypeService;

    public IngredientTypeRestController(IngredientTypeService ingredientTypeService) {
        this.ingredientTypeService = ingredientTypeService;
    }

    @GetMapping(value = "/{ingredientTypeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public IngredientTypeDto getIngredientType(@PathVariable String ingredientTypeId) {
        return ingredientTypeService.getIngredientType(ingredientTypeId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public IngredientTypeDto saveIngredientType(@RequestBody IngredientTypeDto ingredientTypeDto) {
        return ingredientTypeService.saveIngredientType(ingredientTypeDto);

    }
}
