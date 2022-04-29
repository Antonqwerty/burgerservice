package com.example.burgerservice.rest.dto;

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
