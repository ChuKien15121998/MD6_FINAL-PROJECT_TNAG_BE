package com.codegym.service.impl;

import com.codegym.model.FoodCategory;
import com.codegym.repository.IFoodCategoryRepository;
import com.codegym.service.IFoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class FoodCategoryService implements IFoodCategoryService {
    @Autowired
    IFoodCategoryRepository foodCategoryRepository;

    public Iterable<FoodCategory> findAll() {
        return foodCategoryRepository.findAll();
    }

    public Optional<FoodCategory> findById(Long id) {
        return foodCategoryRepository.findById(id);
    }

    public FoodCategory save(FoodCategory foodCategory) {
        return foodCategoryRepository.save(foodCategory);
    }

    public void remove(Long id) {
        foodCategoryRepository.deleteById(id);
    }
}
