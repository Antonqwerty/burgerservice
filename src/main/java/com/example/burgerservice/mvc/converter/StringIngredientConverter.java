package com.example.burgerservice.mvc.converter;

import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.mvc.repository.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StringIngredientConverter implements Converter<String, Ingredient> {
    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    private IngredientRepository ingredientRepository;

    private StringIngredientConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
        Iterable<Ingredient> ingredientIterable = ingredientRepository.findAll();//findAll возвращает iterable
        for (Ingredient ingredient : ingredientIterable) {
            ingredientMap.put(ingredient.getId(), ingredient);
        }
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}
