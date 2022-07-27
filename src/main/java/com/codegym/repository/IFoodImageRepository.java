package com.codegym.repository;

import com.codegym.model.Food;
import com.codegym.model.FoodImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodImageRepository extends JpaRepository<FoodImage, Long> {
    @Query(value = "select * from foodimage where food_id = :id", nativeQuery = true)
    Iterable<FoodImage> findAllByFood (Long id);
}
