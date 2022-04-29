package com.example.burgerservice.rest.mapper;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.rest.dto.BurgerOrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring", uses = OrderStatusMapperToString.class)

public interface BurgerOrderMapper {
    BurgerOrderDto burgerOrderDao2Dto(BurgerOrder burgerOrder);
}
