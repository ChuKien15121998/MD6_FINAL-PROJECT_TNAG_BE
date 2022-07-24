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

    public Optional<CartDetail> findCartDetailByCartAndFood(Cart cart, Food food) {
        return cartDetailRepository.findCartDetailByCartAndFood(cart, food);
    }

    public Iterable<CartDetail> findCartDetailByCart(Cart cart) {
        return cartDetailRepository.findCartDetailByCart(cart);
    }

    public Iterable<CartDetail> findCartDetailByMerchant(Merchant merchant) {
        return cartDetailRepository.findCartDetailByMerchant(merchant);
    }

    public Iterable<CartDetail> findCartDetailByCartAndMerchant(Cart cart, Merchant merchant) {
        return cartDetailRepository.findCartDetailByCartAndMerchant(cart, merchant);
    }

    public Iterable<Merchant> getListMerchantInCart(Cart cart) {
        return cartDetailRepository.getListMerchantInCart(cart);
    }
}
