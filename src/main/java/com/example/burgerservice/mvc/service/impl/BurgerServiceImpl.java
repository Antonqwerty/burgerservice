package com.example.burgerservice.mvc.service.impl;

import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.mvc.repository.BurgerRepository;
import com.example.burgerservice.mvc.service.BurgerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BurgerServiceImpl implements BurgerService {
    private final BurgerRepository burgerRepository;

    @Autowired
    public BurgerServiceImpl(BurgerRepository burgerRepository) {
        this.burgerRepository = burgerRepository;
    }

    @Override
    public void saveBurgerById(Long id) {
      //  burgerRepository.save(burgerRepository.findAllById(id));

    }

    @Override
    public void deleteBurgerById(Long id) {
        Optional<Burger> optionalBurger = burgerRepository.findById(id);
        if (optionalBurger.isEmpty()) {
            log.error("entity with ID = {} was not found", id);
            return;
        }
        log.info("delete burger by id");
        burgerRepository.deleteById(id);
    }

    @Override
    public Iterable<Burger> getAllBurgers() {
        log.info("get all burgers");
        return burgerRepository.findAll();
    }

    @Override
    public Iterable<Burger> saveAllBurgers(Iterable<Burger> burgers) {
        return burgerRepository.saveAll(burgers);
    }

    @Override
    public List<Burger> findBurgersByIngredientsIn(List<Ingredient> ingredients) {
        return burgerRepository.findBurgersByIngredientsIn(ingredients);
    }
}
