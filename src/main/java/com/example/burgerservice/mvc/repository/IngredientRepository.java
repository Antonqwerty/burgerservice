package com.example.burgerservice.mvc.repository;

import com.example.burgerservice.mvc.domain.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    @Query("SELECT i FROM Ingredient i JOIN FETCH  i.ingredientType")
    Iterable<Ingredient> findAllIngredients();
}
