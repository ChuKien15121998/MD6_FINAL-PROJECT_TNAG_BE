package com.codegym.service.impl;

import com.codegym.model.Order;
import com.codegym.repository.IOrderRepository;
import com.codegym.service.IOrderSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService implements IOrderSevice {
    @Autowired
    IOrderRepository orderRepository;

    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void remove(Long id) {
        orderRepository.deleteById(id);
    }
}
