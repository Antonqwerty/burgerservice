package com.example.burgerservice.mvc.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_STATUS")
@Getter
@Setter
public class OrderStatus {
    public OrderStatus(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public OrderStatus() {}

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "STATUS")
    private String status;
}
