package com.codegym.service;

import com.codegym.model.OrderStatus;

import java.util.Optional;

public interface IOrderStatusService extends IGeneralService<OrderStatus> {
    Optional<OrderStatus> findByNameOrderStatus(String name);

}
