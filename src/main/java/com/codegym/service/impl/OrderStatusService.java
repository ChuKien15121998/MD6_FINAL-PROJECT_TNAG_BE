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
}
