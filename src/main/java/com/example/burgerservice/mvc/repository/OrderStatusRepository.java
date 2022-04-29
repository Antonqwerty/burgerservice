package com.example.burgerservice.mvc.repository;

import com.example.burgerservice.mvc.domain.OrderStatus;
import org.springframework.data.repository.CrudRepository;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, String> {
    OrderStatus findByStatusEquals(String status);
}
