package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table( name = "dishes")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Lob
    private String image;
    @NotBlank
    private String description;
    @NotBlank
    private double priceDiscount;
    @NotBlank
    private Boolean recommend;
    @Column (columnDefinition = "BIGINT default 0")
    private Long sold;
    @NotBlank
    @Column(name = "isDelete", columnDefinition = "boolean default true")
    private Boolean isDelete;
    @ManyToOne
    private FoodCategory foodCategory;
    @ManyToOne
    private Merchant merchant;

    public Food() {
    }

    public Food(Long id, String name, String image, String description, double priceDiscount, Boolean recommend, Long sold, Boolean isDelete, FoodCategory foodCategory, Merchant merchant) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.priceDiscount = priceDiscount;
        this.recommend = recommend;
        this.sold = sold;
        this.isDelete = isDelete;
        this.foodCategory = foodCategory;
        this.merchant = merchant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(double priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Long getSold() {
        return sold;
    }

    public void setSold(Long sold) {
        this.sold = sold;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
}
