package com.example.burgerservice.job;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.domain.OrderStatus;
import com.example.burgerservice.mvc.repository.BurgerOrderRepository;
import com.example.burgerservice.rest.service.OrderService;
import com.example.burgerservice.rest.service.OrderStatusService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class StatusJob {
    private Map<String, String> statusMap = new HashMap<>();

    private final OrderService orderService;
    private final OrderStatusService orderStatusService;
    private final BurgerOrderRepository burgerOrderRepository;

    public StatusJob(OrderService orderService, OrderStatusService orderStatusService, BurgerOrderRepository burgerOrderRepository) {
        this.orderStatusService = orderStatusService;
        this.orderService = orderService;
        this.burgerOrderRepository = burgerOrderRepository;
    }

    @Scheduled(initialDelay = 30000, fixedDelay = 5 * 60000)
    public void changeStatus() {
        Iterable<BurgerOrder> burgerOrders = orderService.getAll();
        for (BurgerOrder burgerOrder : burgerOrders) {
            changeOrderStatus(burgerOrder);
        }
    }

    private void changeOrderStatus(BurgerOrder burgerOrder) {
        String newStatus = statusMap.get(burgerOrder.getStatus().getStatus());
        OrderStatus newOrderStatus = orderStatusService.get(newStatus);
        burgerOrder.setStatus(newOrderStatus);
        burgerOrderRepository.save(burgerOrder);
    }

    @PostConstruct
    public void initializeStatusMap() {
        statusMap.put("CREATED", "IN_PROGRESS");
        statusMap.put("IN_PROGRESS", "DONE");
        statusMap.put("DONE", "CANCELED");
        statusMap.put("CANCELED", "CANCELED");
    }
}


