package com.codegym.service.impl;

import com.codegym.model.OrderDetails;
import com.codegym.repository.IOrderDetailRepository;
import com.codegym.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailService implements IOrderDetailService {
    @Autowired
    IOrderDetailRepository orderDetailRepository;

    public Iterable<OrderDetails> findAll() {
        return orderDetailRepository.findAll();
    }

    public Optional<OrderDetails> findById(Long id) {
        return orderDetailRepository.findById(id);
    }

    public OrderDetails save(OrderDetails orderDetails) {
        return orderDetailRepository.save(orderDetails);
    }

    public void remove(Long id) {
        orderDetailRepository.deleteById(id);
    }
}
