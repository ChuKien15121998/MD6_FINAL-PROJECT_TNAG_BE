package com.codegym.repository;

import com.codegym.model.Cart;
import com.codegym.model.CartDetail;
import com.codegym.model.Food;
import com.codegym.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {
    Optional<CartDetail> findCartDetailByCartAndFood(Cart cart, Food food);

    void deleteAllByCartAndMerchant(Cart cart, Merchant merchant);

    void deleteCartDetailByCartAndMerchant (Cart cart, Merchant merchant);

    Iterable<CartDetail> findAllByCartAndMerchant(Cart cart, Merchant merchant);

    Iterable<CartDetail> findCartDetailByCart (Cart cart);

    Iterable<CartDetail> findCartDetailByMerchant (Merchant merchant);

    Iterable<CartDetail> findCartDetailByCartAndMerchant (Cart cart, Merchant merchant);
}
