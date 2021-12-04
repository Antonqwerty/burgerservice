package com.example.burgerservice.rest.service.impl;

import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.mvc.repository.BurgerRepository;
import com.example.burgerservice.rest.dto.BurgerDto;
import com.example.burgerservice.rest.mapper.BurgerMapper;
import com.example.burgerservice.rest.service.BurgerService;
import org.springframework.stereotype.Service;

@Service("BurgerServiceImpl")
public class BurgerServiceImpl implements BurgerService {

    private final BurgerRepository burgerRepository;
    private final BurgerMapper burgerMapper;

    public BurgerServiceImpl(BurgerRepository burgerRepository, BurgerMapper burgerMapper) {
        this.burgerRepository = burgerRepository;
        this.burgerMapper = burgerMapper;
    }

    @Override
    public BurgerDto getBurger(Long id) {
        return burgerMapper.burgerDao2Dto(burgerRepository.findById(id).orElseThrow());
    }

    @Override
    public BurgerDto saveBurger(BurgerDto burgerDto) {
        Burger savedBurger = burgerRepository.save(burgerMapper.burgerDto2Dao(burgerDto));
        return burgerMapper.burgerDao2Dto(savedBurger);
    }

}
