package com.example.burgerservice.mvc.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Data
//@RequiredArgsConstructor
@Entity
@Table(name = "INGREDIENT")
@Getter
public class Ingredient {
    @Column(name = "NAME")
    private String name;
    @Id
    @Column(name = "ID")
    private String id;

    @ManyToMany(mappedBy = "ingredients")
    private List<Burger> burgerList = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "INGREDIENT_TYPE_ID", referencedColumnName = "ID")
    private IngredientType ingredientType;


    public Ingredient() {
    }

    public Ingredient(String name, String id, IngredientType ingredientType) {
        this.name = name;
        this.id = id;
        this.ingredientType = ingredientType;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
