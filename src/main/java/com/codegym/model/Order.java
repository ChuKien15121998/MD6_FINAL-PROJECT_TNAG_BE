package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table( name = "orders")
public class Order {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private Date createAt;
    @ManyToOne
    private OrderStatus orderStatus;
    private double shippingFree = 10;
    @NotBlank
    private double priceTotal;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Merchant merchant;

    public Order() {
    }

    public Order(Long id, Date createAt, OrderStatus orderStatus, double shippingFree, double priceTotal, Customer customer, Merchant merchant) {
        this.id = id;
        this.createAt = createAt;
        this.orderStatus = orderStatus;
        this.shippingFree = shippingFree;
        this.priceTotal = priceTotal;
        this.customer = customer;
        this.merchant = merchant;
    }

    public Order(Date createAt, OrderStatus orderStatus, double shippingFree, double priceTotal, Customer customer, Merchant merchant) {
        this.createAt = createAt;
        this.orderStatus = orderStatus;
        this.shippingFree = shippingFree;
        this.priceTotal = priceTotal;
        this.customer = customer;
        this.merchant = merchant;
    }

    public Order(Date createAt, OrderStatus orderStatus, double priceTotal, Customer customer, Merchant merchant) {
        this.createAt = createAt;
        this.orderStatus = orderStatus;
        this.priceTotal = priceTotal;
        this.customer = customer;
        this.merchant = merchant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getShippingFree() {
        return shippingFree;
    }

    public void setShippingFree(double shippingFree) {
        this.shippingFree = shippingFree;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
}
