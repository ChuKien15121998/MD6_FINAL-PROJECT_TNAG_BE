package com.codegym.service;

import com.codegym.model.OrderStatus;

import java.util.Optional;

public interface IOrderStatusService {
    Optional<OrderStatus> findByNameOrderStatus(String name);

}
