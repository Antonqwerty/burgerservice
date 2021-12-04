package com.example.burgerservice.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class IngredientListWrapperDto {
    private List<IngredientDto> ingredients;
}
