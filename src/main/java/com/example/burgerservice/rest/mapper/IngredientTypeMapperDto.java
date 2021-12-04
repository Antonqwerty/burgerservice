package com.example.burgerservice.rest.mapper;

import com.example.burgerservice.mvc.domain.IngredientType;
import com.example.burgerservice.rest.dto.IngredientTypeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface IngredientTypeMapperDto {
    IngredientType ingredientTypeDto2Dao(IngredientTypeDto ingredientTypeDto);

    IngredientTypeDto ingredientTypeDao2Dto(IngredientType ingredientType);
}
