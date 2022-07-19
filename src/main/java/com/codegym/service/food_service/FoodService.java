package com.codegym.service.food_service;

import com.codegym.model.Food;
import com.codegym.repository.IFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService implements IFoodService {

    @Autowired
    private IFoodRepository foodRepository;

    public Iterable<Food> findAll() {
        return foodRepository.findAll();
    }

    public Optional<Food> findById(Long id) {
        return foodRepository.findById(id);
    }

    public Food save(Food food) {
        return foodRepository.save(food);
    }

    public void remove(Long id) {
        foodRepository.deleteById(id);
    }

    public Page<Food> findAllPageable(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }

    public Page<Food> findAllByMerchantId(Long id, Pageable pageable) {
        return foodRepository.findAllByMerchantId(id, pageable);
    }
}
