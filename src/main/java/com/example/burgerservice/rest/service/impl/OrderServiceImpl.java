package com.example.burgerservice.rest.service.impl;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.repository.BurgerOrderRepository;
import com.example.burgerservice.rest.dto.BurgerOrderDto;
import com.example.burgerservice.rest.mapper.BurgerOrderMapper;
import com.example.burgerservice.rest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Arrays.compare;
import static java.util.Arrays.sort;


@Service("orderServiceRest")
public class OrderServiceImpl implements OrderService {

    private final BurgerOrderRepository burgerOrderRepository;
    private final BurgerOrderMapper burgerOrderMapper;

    @Autowired
    public OrderServiceImpl(BurgerOrderRepository burgerOrderRepository, BurgerOrderMapper burgerOrderMapper) {
        this.burgerOrderRepository = burgerOrderRepository;
        this.burgerOrderMapper = burgerOrderMapper;
    }

    @Override
    public BurgerOrderDto getOrder(Long id) {
        BurgerOrder burgerOrder = burgerOrderRepository.findById(id).get();
        return burgerOrderMapper.burgerOrderDao2Dto(burgerOrder);
    }

    @Override
    public BurgerOrderDto getLastOrder() {
        Iterable<BurgerOrder> burgerOrder = burgerOrderRepository.findAll();
        Optional<BurgerOrder> burgerOrderMax = StreamSupport.stream(burgerOrder.spliterator(), false)
                .max(Comparator.comparing(BurgerOrder::getCreatedAt));
        BurgerOrderDto burgerOrderDto;
        if (burgerOrderMax.isPresent()) {
            burgerOrderDto = burgerOrderMapper.burgerOrderDao2Dto(burgerOrderMax.get());
        } else {
            burgerOrderDto = null;
        }
        return burgerOrderDto;
    }

}
