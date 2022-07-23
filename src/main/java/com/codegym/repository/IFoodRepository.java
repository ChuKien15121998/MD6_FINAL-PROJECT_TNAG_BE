package com.codegym.repository;

import com.codegym.model.Food;
import com.codegym.model.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IFoodRepository extends JpaRepository<Food, Long> {

    @Query(value = "SELECT * FROM foods where is_delete = true and merchant_id = ?1", nativeQuery = true)
    Page<Food> findAllByMerchantId(Long id, Pageable pageable);

    @Query(value = "SELECT * FROM foods where is_delete = true and name like ?1 and merchant_id = ?2", nativeQuery = true)
    Page<Food> findAllByNameContaining(String name, Long id, Pageable pageable);

    @Query(value = "SELECT * FROM foods where is_delete = true", nativeQuery = true)
    Iterable<Food> findAllByMerchant (Merchant merchant);

    Iterable<Food> findAllByMerchantAndDeleteTrue (Merchant merchant);
}
