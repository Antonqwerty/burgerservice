package com.example.burgerservice.job;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.repository.BurgerOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//@Component
@Slf4j
public class OrderCheckJob {
    private final BurgerOrderRepository burgerOrderRepository;

    @Autowired
    public OrderCheckJob(BurgerOrderRepository burgerOrderRepository) {
        this.burgerOrderRepository = burgerOrderRepository;
    }

    @Scheduled(initialDelay = 40000, fixedDelay = 3000)
    public void checkOrdersDate() {
        log.info("Job successfully started");
        List<BurgerOrder> orders = StreamSupport.stream(burgerOrderRepository.findAll().spliterator(), false)
                .filter(burgerOrder ->
                        burgerOrder.getCreatedAt().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
        burgerOrderRepository.deleteAll(orders);
        log.info("Job successfully ended");
    }
}
