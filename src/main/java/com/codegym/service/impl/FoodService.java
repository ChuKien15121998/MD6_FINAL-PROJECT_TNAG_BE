package com.codegym.service.impl;

import com.codegym.model.Food;
import com.codegym.model.Merchant;
import com.codegym.repository.IFoodRepository;
import com.codegym.service.IFoodService;
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

    public Iterable<Food> findAllByMerchant(Merchant merchant) {
        return foodRepository.findAllByMerchant(merchant);
    }

    @Override
    public Iterable<Food> findAllByMerchantAndIsDeleteTrue(Merchant merchant) {
        return foodRepository.findAllByMerchantAndIsDeleteTrue(merchant);
    }

    public Iterable<Food> listSoldTop8() {
        return foodRepository.listSoldTop8();
    }

    public Iterable<Food> listNewestFood() {
        return foodRepository.listNewestFood();
    }

    public Iterable<Food> listSoldTop3ByMerchant(Long merchantId) {
        return foodRepository.listSoldTop3ByMerchant(merchantId);
    }

    public Page<Food> findAllByNameContaining(String name, Long id, Pageable pageable) {
        return foodRepository.findAllByNameContaining(name, id, pageable);
    }
}
