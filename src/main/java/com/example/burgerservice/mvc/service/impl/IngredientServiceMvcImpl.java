package com.example.burgerservice.mvc.service.impl;

import com.example.burgerservice.constant.CacheConstants;
import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.mvc.domain.IngredientType;
import com.example.burgerservice.mvc.repository.IngredientRepository;
import com.example.burgerservice.mvc.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Slf4j
public class IngredientServiceMvcImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientServiceMvcImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @PostConstruct
    private void saveIngredients() {
        IngredientType ingredientTypeCheese = new IngredientType("CH", "CHEESE");
        IngredientType ingredientTypeMeat = new IngredientType("MT", "MEAT");
        IngredientType ingredientTypeSouce = new IngredientType("SC", "SOUCE");
        //NEW INGREDIENTTYPE
        IngredientType ingredientTypeBread = new IngredientType("BR", "BREAD");
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("Chedder", "CH", ingredientTypeCheese),
                new Ingredient("Parmezan", "PM", ingredientTypeCheese),
                new Ingredient("Steak", "ST", ingredientTypeMeat),
                new Ingredient("Chicken", "CHM", ingredientTypeMeat),
                new Ingredient("Barbeque", "BBQ", ingredientTypeSouce),
                new Ingredient("Ceasar", "CS", ingredientTypeSouce),
                new Ingredient("White_br", "WB", ingredientTypeBread),
                new Ingredient("Brown_br", "BB", ingredientTypeBread)
        );
        log.info("Start saving ingredients");
        saveAllIngredients(ingredients);
        log.info("Ingredients have saved");
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

    @Cacheable(cacheNames = CacheConstants.INGREDIENTS_AND_TYPES)
    public Map<String, List<Ingredient>> getIngredients() {
        Iterable<Ingredient> ingredients = getAllIngredients();
        Set<IngredientType> ingredientTypes = fetchAllIngredientTypes(ingredients);
        Map<String, List<Ingredient>> ingredientsMap = new HashMap<>();
        for (IngredientType ingredientType : ingredientTypes) {
            ingredientsMap.put(ingredientType.getName().toUpperCase(), filterByType(ingredientType, ingredients));
        }
        return ingredientsMap;
    }

    private List<Ingredient> filterByType(IngredientType type, Iterable<Ingredient> ingredientList) {
        List<Ingredient> resultIngredient = new ArrayList<>();
        for (Ingredient ingredient : ingredientList) {
            if (ingredient.getIngredientType().equals(type)) {
                resultIngredient.add(ingredient);
            }
        }
        return resultIngredient;
    }

    private Set<IngredientType> fetchAllIngredientTypes(Iterable<Ingredient> ingredients) {
        Set<IngredientType> typeList = new HashSet<>();
        for (Ingredient ing : ingredients) {
            typeList.add(ing.getIngredientType());
        }
        return typeList;
    }
}
