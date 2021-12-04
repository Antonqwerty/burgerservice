package com.example.burgerservice.rest.controller;

import com.example.burgerservice.rest.constant.Currency;
import com.example.burgerservice.rest.dto.IngredientTypeDto;
import com.example.burgerservice.rest.service.IngredientTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/ingredientType")
public class IngredientTypeRestController {
    public final IngredientTypeService ingredientTypeService;

    @Autowired
    public IngredientTypeRestController(IngredientTypeService ingredientTypeService) {
        this.ingredientTypeService = ingredientTypeService;
    }

    @GetMapping("/{ingredientTypeId}")
    public IngredientTypeDto getIngredientType(@PathVariable String ingredientTypeId) {
        log.info("getting type");
        return ingredientTypeService.getIngredientType(ingredientTypeId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public IngredientTypeDto saveIngredientType(@RequestBody IngredientTypeDto ingredientTypeDto/*,
                                                *//*@RequestParam(name = "currency") String currency*/) {
        /*if (!Currency.currencyList.containsKey(currency)) {
            log.warn("There is no currency = {} in directory", currency);
            log.debug("Changing currency to RUB");
            currency = "Rub";
        }*/
        log.info("saving type");
        return ingredientTypeService.saveIngredientType(ingredientTypeDto);

    }
}
