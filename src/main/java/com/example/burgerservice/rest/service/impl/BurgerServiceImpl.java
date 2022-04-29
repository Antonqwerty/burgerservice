package com.example.burgerservice.rest.service.impl;

import com.example.burgerservice.constant.CacheConstants;
import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.mvc.repository.BurgerRepository;
import com.example.burgerservice.rest.dto.BurgerDto;
import com.example.burgerservice.rest.mapper.BurgerMapper;
import com.example.burgerservice.rest.service.BurgerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BurgerServiceImpl implements BurgerService {

    private final BurgerRepository burgerRepository;
    private final BurgerMapper burgerMapper;

    public BurgerServiceImpl(BurgerRepository burgerRepository, BurgerMapper burgerMapper) {
        this.burgerRepository = burgerRepository;
        this.burgerMapper = burgerMapper;
    }

    @Override
    @Cacheable(cacheNames = CacheConstants.BURGER, key = "#id", unless = "#result==null")
    //unless - кэшируем пока не будет возвращен null
    //sync - сначала в кэш потом возвращаем
    public BurgerDto getBurger(Long id) {
        return burgerMapper.burgerDao2Dto(burgerRepository.findById(id).orElseThrow());
    }

    @Override
    public BurgerDto saveBurger(BurgerDto burgerDto) {
        Burger savedBurger = burgerRepository.save(burgerMapper.burgerDto2Dao(burgerDto));
        return burgerMapper.burgerDao2Dto(savedBurger);
    }

    @Override
    @Cacheable(cacheNames = CacheConstants.BURGERS)
    public Iterable<BurgerDto> findAll() {
        Iterable<Burger> allBurgersDao = burgerRepository.findAll();
        List<BurgerDto> allBurgersDto = new ArrayList<>();
        for (Burger burger : allBurgersDao) {
            allBurgersDto.add(burgerMapper.burgerDao2Dto(burger));
        }
        log.info("get all burgersDTO");
        return allBurgersDto;
    }

    @Override
    public BurgerDto updateBurger(BurgerDto burgerDto) {
        if (burgerRepository.findById(burgerDto.getId()).isPresent()) {
            Burger burgerForUpdate = burgerMapper.burgerDto2Dao(burgerDto);
            Burger updatedBurger = burgerRepository.save(burgerForUpdate);
            return burgerMapper.burgerDao2Dto(updatedBurger);
        }
        return null;
    }

    @Override
    public void deleteBurger(Long id) {
        burgerRepository.deleteById(id);
    }

}
