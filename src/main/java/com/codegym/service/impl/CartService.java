package com.codegym.service.impl;

import com.codegym.model.Cart;
import com.codegym.model.Customer;
import com.codegym.repository.ICartRepository;
import com.codegym.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    ICartRepository cartRepository;

    public Iterable<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public void remove(Long id) {
        cartRepository.deleteById(id);
    }

    public Optional<Cart> findCartByCustomer(Customer customer) {
        return cartRepository.findCartByCustomer(customer);
    }
}
