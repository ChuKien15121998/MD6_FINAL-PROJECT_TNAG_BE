package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name = "orderstatus", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "nameOrderStatus"
        })
})
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameOrderStatus;

    public OrderStatus() {
    }

    public OrderStatus(Long id, String nameOrderStatus) {
        this.id = id;
        this.nameOrderStatus = nameOrderStatus;
    }

    public OrderStatus(String nameOrderStatus) {
        this.nameOrderStatus = nameOrderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOrderStatus() {
        return nameOrderStatus;
    }

    public void setNameOrderStatus(String nameOrderStatus) {
        this.nameOrderStatus = nameOrderStatus;
    }
}
