package com.codegym.service;

import com.codegym.model.Cart;
import com.codegym.model.CartDetail;
import com.codegym.model.Food;
import com.codegym.model.Merchant;

import java.math.BigInteger;
import java.util.Optional;

public interface ICartDetailService extends IGeneralService<CartDetail> {
    Optional<CartDetail> findCartDetailByCartAndFood(Cart cart, Food food);
    Iterable<CartDetail> findCartDetailByCart (Cart cart);

    Iterable<CartDetail> findCartDetailByMerchant (Merchant merchant);
    Iterable<CartDetail> findCartDetailByCartAndMerchant (Cart cart, Merchant merchant);

}
