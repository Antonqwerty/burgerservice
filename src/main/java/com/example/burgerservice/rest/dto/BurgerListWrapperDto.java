package com.example.burgerservice.rest.dto;

import lombok.Data;


@Data
public class BurgerListWrapperDto {
    private Iterable <BurgerDto> burgers;
}
