package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table (name = "foodimage")
public class FoodImage {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String image;
    @ManyToOne
    private Food food;

    public FoodImage() {
    }

    public FoodImage(Long id, String image, Food food) {
        this.id = id;
        this.image = image;
        this.food = food;
    }

    public FoodImage(String image, Food food) {
        this.image = image;
        this.food = food;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
