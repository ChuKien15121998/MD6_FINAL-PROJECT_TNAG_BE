package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.model.Merchant;
import com.codegym.model.Order;
import com.codegym.model.OrderStatus;
import org.springframework.data.jpa.repository.Query;

public interface IOrderService extends IGeneralService<Order> {
    Iterable<Order> findAllByMerchant(Merchant merchant);

    Iterable<Order> findAllByCustomer(Customer customer);

    Iterable<Order> findAllByOrderStatusAndMerchant(OrderStatus orderStatus, Merchant merchant);

    Iterable<Order> merchantSearch(String search, Long id);

    Iterable<Order> merchantSearchAccepted(String search, Long id);

    Iterable<Order> merchantSearchDenied(String search, Long id);

    Iterable<Order> merchantSearchWait(String search, Long id);
}
