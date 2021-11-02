package com.example.burgerservice.mvc.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "BURGER_ORDER")
@Slf4j
public class BurgerOrder {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @Column(name = "DELIVERY_NAME")
    @NotBlank(message="Delivery name is required")
    private String deliveryName;

    @Column(name = "DELIVERY_STREET")
    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @Column(name = "DELIVERY_CITY")
    @NotBlank(message="City is required")
    private String deliveryCity;

    @Column(name = "DELIVERY_STATE")
    @NotBlank(message="State is required")
    private String deliveryState;

    @Column(name = "DELIVERY_ZIP")
    @NotBlank(message="Zip code is required")
    private String deliveryZip;

    @Column(name = "CC_NUMBER")
   // @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Column(name = "CC_EXP")
   // @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;

    @Column(name = "CVV")
    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "burgerOrder")
    private List<Burger> burgers = new ArrayList<>();


    public void addBurger(Burger burger) {
        if (burger == null) {
            log.error("get burger null value");
            throw new IllegalArgumentException();
        }
        burgers.add(burger);
    }

    @PrePersist
    public void createdAt() {
        createdAt = LocalDateTime.now();
    }
}
