package com.example.burgerservice.rest.mapper;

import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.rest.dto.IngredientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = IngredientTypeMapper.class)
public interface IngredientMapper {
    IngredientDto ingredientDao2Dto(Ingredient ingredient);

    Ingredient ingredientDto2Dao(IngredientDto ingredient);
}
