package com.codegym.repository;

import com.codegym.model.Food;
import com.codegym.model.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFoodRepository extends JpaRepository<Food, Long> {

    Page<Food> findAllByMerchantId(Long id, Pageable pageable);

    Page<Food> findAllByNameContaining(Pageable pageable, String name);

    Iterable<Food> findAllByMerchant (Merchant merchant);

}
