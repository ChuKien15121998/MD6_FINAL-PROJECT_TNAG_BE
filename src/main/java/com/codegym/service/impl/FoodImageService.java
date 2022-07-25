package com.codegym.service.impl;

import com.codegym.model.Food;
import com.codegym.model.FoodImage;
import com.codegym.repository.IFoodImageRepository;
import com.codegym.service.IFoodImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class FoodImageService implements IFoodImageService {
    @Autowired
    IFoodImageRepository foodImageRepository;
    public Iterable<FoodImage> findAll() {
        return null;
    }

    public Optional<FoodImage> findById(Long id) {
        return Optional.empty();
    }

    public FoodImage save(FoodImage foodImage) {
        return null;
    }

    public void remove(Long id) {

    }

    public Iterable<FoodImage> findFoodImageByFood(Food food) {
        return foodImageRepository.findFoodImageByFood(food);
    }
}
