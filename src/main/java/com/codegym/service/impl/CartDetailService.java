package com.codegym.service.impl;

import com.codegym.model.Cart;
import com.codegym.model.CartDetail;
import com.codegym.model.Food;
import com.codegym.model.Merchant;
import com.codegym.repository.ICartDetailRepository;
import com.codegym.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartDetailService implements ICartDetailService {
    @Autowired
    ICartDetailRepository cartDetailRepository;

    public Iterable<CartDetail> findAll() {
        return cartDetailRepository.findAll();
    }

    public Optional<CartDetail> findById(Long id) {
        return cartDetailRepository.findById(id);
    }

    public CartDetail save(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    public void remove(Long id) {
        cartDetailRepository.deleteById(id);
    }

    @Override
    public Iterable<CartDetail> findAllByCartAndMerchant(Cart cart, Merchant merchant) {
        return cartDetailRepository.findAllByCartAndMerchant(cart, merchant);
    }

    public Optional<CartDetail> findCartDetailByCartAndFood(Cart cart, Food food) {
        return cartDetailRepository.findCartDetailByCartAndFood(cart, food);
    }

    @Override
    public void deleteCartDetailByCartAndMerchant(Cart cart, Merchant merchant) {
        cartDetailRepository.deleteCartDetailByCartAndMerchant(cart, merchant);
    }
}
