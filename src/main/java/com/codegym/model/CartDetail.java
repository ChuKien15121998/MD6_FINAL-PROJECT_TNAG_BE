package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name = "cartdetails")
public class CartDetail {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Food food;
    @ManyToOne
    private Merchant merchant;
    private double quantity;
    private double totalPrice;

    public CartDetail() {
    }

    public CartDetail(Long id, Cart cart, Food food, double quantity, double totalPrice) {
        this.id = id;
        this.cart = cart;
        this.food = food;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public CartDetail(Cart cart, Food food, double quantity, double totalPrice) {
        this.cart = cart;
        this.food = food;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public CartDetail(Cart cart, Food food, Merchant merchant, double quantity, double totalPrice) {
        this.cart = cart;
        this.food = food;
        this.merchant = merchant;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
