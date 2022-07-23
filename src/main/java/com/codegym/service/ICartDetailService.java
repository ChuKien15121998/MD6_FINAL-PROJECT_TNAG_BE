package com.codegym.service;

import com.codegym.model.Cart;
import com.codegym.model.CartDetail;
import com.codegym.model.Food;

import java.util.Optional;

public interface ICartDetailService extends IGeneralService<CartDetail> {
    Optional<CartDetail> findCartDetailByCartAndFood(Cart cart, Food food);
}
