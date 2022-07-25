package com.codegym.repository;

import com.codegym.model.Customer;
import com.codegym.model.Merchant;
import com.codegym.model.Order;
import com.codegym.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    Iterable<Order> findAllByMerchantOrderByCreateAt(Merchant merchant);

    Iterable<Order> findAllByCustomerOrderByCreateAt(Customer customer);

    Iterable<Order> findAllByOrderStatus(OrderStatus orderStatus);


}