package com.example.burgerservice.rest.service.impl;

import com.example.burgerservice.mvc.domain.IngredientType;
import com.example.burgerservice.mvc.repository.IngredientTypeRepository;
import com.example.burgerservice.rest.dto.IngredientTypeDto;
import com.example.burgerservice.rest.mapper.IngredientTypeMapper;
import com.example.burgerservice.rest.mapper.IngredientTypeMapperDto;
import com.example.burgerservice.rest.service.IngredientTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service("IngredientTypeServiceRest")
public class IngredientTypeServiceImpl implements IngredientTypeService {
    private final IngredientTypeRepository ingredientTypeRepository;
    private final IngredientTypeMapperDto ingredientTypeMapperDto;

    @Autowired
    public IngredientTypeServiceImpl(IngredientTypeRepository ingredientTypeRepository, IngredientTypeMapperDto ingredientTypeMapperDto) {
        this.ingredientTypeRepository = ingredientTypeRepository;
        this.ingredientTypeMapperDto = ingredientTypeMapperDto;
    }

    @Override
    public IngredientTypeDto getIngredientType(String id) {
        return ingredientTypeMapperDto.ingredientTypeDao2Dto(ingredientTypeRepository.findById(id).orElseThrow(() -> {
            return new EntityNotFoundException();
        }));
    }

    @Override
    public IngredientTypeDto saveIngredientType(IngredientTypeDto ingredientTypeDto) {
        IngredientType savedIngredientType = ingredientTypeRepository.save(ingredientTypeMapperDto.ingredientTypeDto2Dao(ingredientTypeDto));
        return ingredientTypeMapperDto.ingredientTypeDao2Dto(savedIngredientType);
    }

}
