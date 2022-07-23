package com.codegym.repository;

import com.codegym.model.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFoodCategoryRepository extends JpaRepository<FoodCategory, Long> {

}
