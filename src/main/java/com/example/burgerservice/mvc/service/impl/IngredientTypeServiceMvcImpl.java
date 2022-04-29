package com.example.burgerservice.mvc.service.impl;

import com.example.burgerservice.mvc.domain.IngredientType;
import com.example.burgerservice.mvc.repository.IngredientTypeRepository;
import com.example.burgerservice.mvc.service.IngredientTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class IngredientTypeServiceMvcImpl implements IngredientTypeService {
    private final IngredientTypeRepository ingredientTypeRepository;
    @Autowired
    public IngredientTypeServiceMvcImpl(IngredientTypeRepository ingredientTypeRepository) {
        this.ingredientTypeRepository = ingredientTypeRepository;

    }

    @Override
    public List<IngredientType> getAllTypes() {
        Iterable<IngredientType> ingredientTypeIterable = ingredientTypeRepository.findAll();
        List<IngredientType> ingredientTypeList = new ArrayList<>();
        for (IngredientType type : ingredientTypeIterable) {
            ingredientTypeList.add(type);
        }
        return ingredientTypeList;
    }
}
