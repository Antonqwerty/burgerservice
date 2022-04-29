package com.example.burgerservice.rest.dto;

import com.example.burgerservice.mvc.domain.Burger;
import lombok.Data;

import java.time.LocalDateTime;


@Data//аннотацию можно использовать с dto объектами
public class BurgerOrderDto {

    private Long id;

    private String deliveryName;

    private String deliveryStreet;

    private String deliveryCity;

    private String deliveryState;

    private String deliveryZip;

    private String ccNumber;

    private String ccExpiration;

    private String ccCVV;

    private LocalDateTime createdAt;

    private String status;
}
