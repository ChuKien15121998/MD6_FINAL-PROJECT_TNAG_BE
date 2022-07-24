package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.model.Merchant;
import com.codegym.model.Order;

public interface IOrderSevice extends IGeneralService<Order> {
    Iterable<Order> findAllByMerchant(Merchant merchant);

    Iterable<Order> findAllByCustomer(Customer customer);
}
