package com.example.burgerservice.rest.service.impl;

import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.mvc.repository.IngredientRepository;
import com.example.burgerservice.rest.dto.IngredientDto;
import com.example.burgerservice.rest.dto.IngredientListWrapperDto;
import com.example.burgerservice.rest.mapper.IngredientMapper;
import com.example.burgerservice.rest.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("IngredientServiceRest")
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientMapper = ingredientMapper;
    }

    @Override
    public IngredientDto getIngredient(String id) {
        return ingredientMapper.ingredientDao2Dto(ingredientRepository.findById(id).orElseThrow(() -> {
            return new EntityNotFoundException();
        }));
    }

    @Override
    public IngredientDto saveIngredient(IngredientDto ingredient) {
        Ingredient savedIngredient = ingredientRepository.save(ingredientMapper.ingredientDto2Dao(ingredient));
        return ingredientMapper.ingredientDao2Dto(savedIngredient);
    }

    @Override
    public IngredientListWrapperDto getAllIngredients() {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        List<IngredientDto> ingredientsList = new ArrayList<>();
        for (Ingredient i : ingredients) {
          ingredientsList.add(ingredientMapper.ingredientDao2Dto(i));

        }
        IngredientListWrapperDto ingredientListWrapperDto = new IngredientListWrapperDto();
        ingredientListWrapperDto.setIngredients(ingredientsList);
        return ingredientListWrapperDto;
    }

}
