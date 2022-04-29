package com.example.burgerservice.rest.mapper;

import com.example.burgerservice.mvc.domain.OrderStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = OrderStatusMapperToString.class)
public interface OrderStatusMapper {
    OrderStatus string2OrderStatus(String orderStatus);

    String orderStatus2string(OrderStatus orderStatus);
}
