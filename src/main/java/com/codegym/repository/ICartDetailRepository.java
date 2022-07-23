package com.codegym.repository;

import com.codegym.model.Cart;
import com.codegym.model.CartDetail;
import com.codegym.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {
    Optional<CartDetail> findCartDetailByCartAndFood(Cart cart, Food food);
}
