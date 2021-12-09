package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.mvc.domain.IngredientType;
import com.example.burgerservice.mvc.service.BurgerService;
import com.example.burgerservice.mvc.service.IngredientService;
import com.example.burgerservice.mvc.service.IngredientTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/design")
@Slf4j
@SessionAttributes("burgerOrder")
public class DesignBurgerController {

    private final IngredientService ingredientService;
    private final IngredientTypeService ingredientTypeService;
    private final BurgerService burgerService;

    @Autowired
    public DesignBurgerController(IngredientService ingredientService, IngredientTypeService ingredientTypeService,
                                  BurgerService burgerService) {
        this.ingredientService = ingredientService;
        this.ingredientTypeService = ingredientTypeService;
        this.burgerService = burgerService;
    }

    @GetMapping
    public String getDesignForm(Model model) {

        model.addAttribute("burger", new Burger());

        return "design";
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

    @PostMapping
    public String processBurgerDesign(@Valid Burger burger, Errors error, @ModelAttribute BurgerOrder order) {
        if (error.hasErrors()) {
            return "design";
        }
        System.out.println(burger.toString());
        order.addBurger(burger);
        return "redirect:/orders/current";
    }

    @ModelAttribute(name = "burgerOrder")
    public BurgerOrder getOrder() {
        return new BurgerOrder();
    }



    @ModelAttribute //данный метод запускается каждый раз, чтобы добавить ингриденты в модель
    private void addIngredients(Model model) {
        log.info("Creating ingredients");
        Iterable<Ingredient> ingredients = ingredientService.getAllIngredients();
        Set<IngredientType> ingredientTypes = fetchAllIngredientTypes(ingredients);
        for (IngredientType ingredientType : ingredientTypes) {
            model.addAttribute(ingredientType.getName().toUpperCase(), filterByType(ingredientType, ingredients));
        }
        log.debug("List of ingredients{}", ingredients);
    }

    private Set<IngredientType> fetchAllIngredientTypes(Iterable<Ingredient> ingredients) {
        Set<IngredientType> typeList = new HashSet<>();
        for (Ingredient ing : ingredients) {
            typeList.add(ing.getIngredientType());
        }
        return typeList;
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
                //NEW INGR
                new Ingredient("White_br", "WB", ingredientTypeBread),
                new Ingredient("Brown_br", "BB", ingredientTypeBread)
        );
        log.info("Start saving ingredients");
        ingredientService.saveAllIngredients(ingredients);
        log.info("Ingredients have saved");
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
        burgerService.saveAllBurgers(burgers);
        findBurgerByIngredients();
    }

    private void findBurgerByIngredients() {
        IngredientType ingredientTypeCheese = new IngredientType("CH", "CHEESE");
        Ingredient ingredient = new Ingredient("Chedder", "CH", ingredientTypeCheese);
        burgerService.findBurgersByIngredientsIn(Arrays.asList(ingredient));
    }
}
