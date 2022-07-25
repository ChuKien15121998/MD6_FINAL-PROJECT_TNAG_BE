package com.codegym.service.impl;

import com.codegym.model.OrderStatus;
import com.codegym.repository.IOrderStatusRepository;
import com.codegym.service.IOrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderStatusService implements IOrderStatusService {

    @Autowired
    IOrderStatusRepository orderStatusRepository;

    @Override
    public Optional<OrderStatus> findByNameOrderStatus(String name) {
        return orderStatusRepository.findByNameOrderStatus(name);
    }

    public Iterable<OrderStatus> findAll() {
        return orderStatusRepository.findAll();
    }

    public Optional<OrderStatus> findById(Long id) {
        return orderStatusRepository.findById(id);
    }

    public OrderStatus save(OrderStatus orderStatus) {
        return orderStatusRepository.save(orderStatus);
    }

    public void remove(Long id) {
        orderStatusRepository.deleteById(id);
    }
}
