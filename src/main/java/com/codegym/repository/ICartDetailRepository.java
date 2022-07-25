package com.codegym.repository;

import com.codegym.model.Cart;
import com.codegym.model.CartDetail;
import com.codegym.model.Food;
import com.codegym.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {
    Optional<CartDetail> findCartDetailByCartAndFood(Cart cart, Food food);

    Iterable<CartDetail> findCartDetailByCart (Cart cart);

    Iterable<CartDetail> findCartDetailByMerchant (Merchant merchant);

    Iterable<CartDetail> findCartDetailByCartAndMerchant (Cart cart, Merchant merchant);

    @Query(value = "SELECT DISTINCT merchant_id FROM cartdetails", nativeQuery = true)
    Iterable<Integer> getListMerchantInCart(Cart cart);
}
