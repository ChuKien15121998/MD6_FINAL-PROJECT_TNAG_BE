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
    private Long image;
    @ManyToOne
    private Food food;

    public FoodImage() {
    }

    public FoodImage(Long id, Long image, Food food) {
        this.id = id;
        this.image = image;
        this.food = food;
    }

    public FoodImage(Long image, Food food) {
        this.image = image;
        this.food = food;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImage() {
        return image;
    }

    public void setImage(Long image) {
        this.image = image;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
