package com.example.burgerservice.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class BurgerListWrapperDto {
    private Iterable <BurgerDto> burgers;
}
