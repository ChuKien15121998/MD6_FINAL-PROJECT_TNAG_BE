package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "foods")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String avatar;
    private Long price;
    private String description;
    private Long priceDiscount;
    private Long purchases;
    private Boolean recommend;
    @ManyToOne
    private FoodCategory foodCategory;
    private Boolean status;
    @ManyToOne
    private Merchant merchant;
}
