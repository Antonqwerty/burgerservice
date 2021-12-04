package com.example.burgerservice.mvc.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "INGREDIENT_TYPE")
@Getter
@Data
public class IngredientType {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "ingredientType")
    private List<Ingredient> ingredients = new ArrayList<>();

    public IngredientType() {}

    public IngredientType(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
