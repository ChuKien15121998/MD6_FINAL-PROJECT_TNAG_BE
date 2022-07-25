package com.codegym.repository;

import com.codegym.model.Food;
import com.codegym.model.FoodImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodImageRepository extends JpaRepository<FoodImage, Long> {
    Iterable<FoodImage> findFoodImageByFood (Food food);
}
