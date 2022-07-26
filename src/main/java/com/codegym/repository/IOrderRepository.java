package com.codegym.repository;

import com.codegym.model.Customer;
import com.codegym.model.Merchant;
import com.codegym.model.Order;
import com.codegym.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    Iterable<Order> findAllByMerchantOrderByCreateAt(Merchant merchant);
//    @Query(value = "select * from orders where merchant_id = :id;", nativeQuery = true)
    Iterable<Order> findAllByMerchant(Merchant merchant);

    Iterable<Order> findAllByCustomerOrderByCreateAt(Customer customer);

    Iterable<Order> findAllByOrderStatus(OrderStatus orderStatus);

    @Query(value = "select * from orders where id like :search or (customer_id in ((select id from customer where name like :search) union (select id from customer where phone_number like :search)))", nativeQuery = true)
    Iterable<Order> merchantSearch(String search);
}