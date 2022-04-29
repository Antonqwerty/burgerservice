package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/design")
@Slf4j
@SessionAttributes("burgerOrder")
public class DesignBurgerController {

    private final IngredientService ingredientService;

    public DesignBurgerController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public String getDesignForm(Model model) {
        model.addAttribute("burger", new Burger());
        return "design";
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
        ingredientService.getIngredients().forEach(model::addAttribute);
    }
}
