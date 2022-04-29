package com.example.burgerservice.mvc.service.impl;

import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.mvc.domain.IngredientType;
import com.example.burgerservice.mvc.repository.BurgerRepository;
import com.example.burgerservice.mvc.service.BurgerService;
import com.example.burgerservice.mvc.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BurgerServiceMvcImpl implements BurgerService {
    private final BurgerRepository burgerRepository;
    private final IngredientService ingredientService;

    public BurgerServiceMvcImpl(BurgerRepository burgerRepository, IngredientService ingredientService) {
        this.burgerRepository = burgerRepository;
        this.ingredientService = ingredientService;
    }

    @PostConstruct
    private void addInitialBurger() {
        Burger burger1 = new Burger();
        Burger burger2 = new Burger();
        burger1.setName("firstBurger");
        burger2.setName("secondBurger");
        Iterable<Ingredient> ingredients = ingredientService.getAllIngredients();
        List<Ingredient> ingredientList = new ArrayList<>();
        for (Ingredient i : ingredients) {
            ingredientList.add(i);
        }
        burger1.setIngredients(ingredientList);
        burger2.setIngredients(ingredientList);
        List<Burger> burgers = new ArrayList<>();
        burgers.add(burger1);
        burgers.add(burger2);
        saveAllBurgers(burgers);
        findBurgerByIngredients();
    }

    @Override
    public void saveBurgerById(Long id) {
    }

    @Override
    public void deleteBurgerById(Long id) {
        Optional<Burger> optionalBurger = burgerRepository.findById(id);
        if (optionalBurger.isEmpty()) {
            log.error("entity with ID = {} was not found", id);
            return;
        }
        log.info("delete burger by id");
        burgerRepository.deleteById(id);
    }

    @Override
    public Iterable<Burger> getAllBurgers() {
        log.info("get all burgers");
        return burgerRepository.findAll();
    }

    @Override
    public Iterable<Burger> saveAllBurgers(Iterable<Burger> burgers) {
        return burgerRepository.saveAll(burgers);
    }

    @Override
    public List<Burger> findBurgersByIngredientsIn(List<Ingredient> ingredients) {
        return burgerRepository.findBurgersByIngredientsIn(ingredients);
    }

    private void findBurgerByIngredients() {
        IngredientType ingredientTypeCheese = new IngredientType("CH", "CHEESE");
        Ingredient ingredient = new Ingredient("Chedder", "CH", ingredientTypeCheese);
        findBurgersByIngredientsIn(Arrays.asList(ingredient));
    }
}
