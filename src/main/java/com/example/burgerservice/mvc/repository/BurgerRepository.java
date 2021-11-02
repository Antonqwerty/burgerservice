package com.example.burgerservice.mvc.repository;

import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.mvc.domain.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BurgerRepository extends CrudRepository<Burger, Long> {

    Object findAllById(Long id);

    List<Burger> findBurgersByIngredientsIn(List<Ingredient> ingredients);
}
