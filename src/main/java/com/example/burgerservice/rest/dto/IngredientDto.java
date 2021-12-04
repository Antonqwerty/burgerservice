package com.example.burgerservice.rest.dto;

import com.example.burgerservice.mvc.domain.IngredientType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IngredientDto {
   // @JsonProperty("Name")
    private String name;

    //@JsonIgnore
    private String id;

    private String ingredientType;

    //  private IngredientType ingredientType;

}
