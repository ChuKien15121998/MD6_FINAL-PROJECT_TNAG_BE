package com.codegym.service;

import com.codegym.model.Food;
import com.codegym.model.Merchant;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IFoodService extends IGeneralService<Food> {

    Iterable<Food> findAll();

    Optional<Food> findById(Long id);

    Food save(Food food);

    void remove(Long id);

    Page<Food> findAllPageable(Pageable pageable);

    Page<Food> findAllByMerchantId(Long id, Pageable pageable);

    Iterable<Food> findAllByMerchant (Merchant merchant);

    Iterable<Food> findAllByMerchantAndIsDeleteTrue (Merchant merchant);

}
