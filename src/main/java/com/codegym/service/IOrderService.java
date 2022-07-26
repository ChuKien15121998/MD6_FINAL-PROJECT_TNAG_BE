package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.model.Merchant;
import com.codegym.model.Order;
import com.codegym.model.OrderStatus;

public interface IOrderService extends IGeneralService<Order> {
    Iterable<Order> findAllByMerchant(Long id);

    Iterable<Order> findAllByCustomer(Customer customer);

    Iterable<Order> findAllByOrderStatus(OrderStatus orderStatus);

    Iterable<Order> merchantSearch(String search);

}
