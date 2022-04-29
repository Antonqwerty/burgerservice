package com.example.burgerservice.mvc.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDER_STATUS")
@Getter
@Setter
public class OrderStatus {
    public OrderStatus() {}

    public OrderStatus(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STATUS")
    private String status;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    List<BurgerOrder> burgerOrderList = new ArrayList<>();

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
