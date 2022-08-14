package com.codegym.repository;

import com.codegym.model.Customer;
import com.codegym.model.Merchant;
import com.codegym.model.Order;
import com.codegym.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    Iterable<Order> findAllByMerchantOrderByCreateAt(Merchant merchant);

    Iterable<Order> findAllByCustomerOrderByCreateAt(Customer customer);

    Iterable<Order> findAllByOrderStatusAndMerchant(OrderStatus orderStatus, Merchant merchant);

    @Query(value = "select * from orders where id like :search or (customer_id in ((select id from customer where name like :search) union (select id from customer where phone_number like :search))) and merchant_id = :id", nativeQuery = true)
    Iterable<Order> merchantSearch(String search, Long id);

    @Query(value = "select * from orders where id like :search or(id in (select order_id from orderdetails where food_id in (select id from foods where name like :search))) or (merchant_id in ((select id from merchant where name like :search) union (select id from merchant where address like :search))) and customer_id = :id", nativeQuery = true)
    Iterable<Order> getListOrderbyCustomerSearch(String search, Long id);

//    @Query(value = "select * from orders where id like :search or (merchant_id in ((select id from merchant where name like :search) union (select id from merchant where address like :search))) and customer_id = :id;", nativeQuery = true)
//    Iterable<Order> getListOrderbyCustomerSearch(String search, Long id);

    @Query(value = "select * from orders where (id like :search or (customer_id in ((select id from customer where name like :search) union (select id from customer where phone_number like :search)))) and order_status_id = 1 and merchant_id = :id", nativeQuery = true)
    Iterable<Order> merchantSearchAccepted(String search, Long id);

    @Query(value = "select * from orders where (id like :search or (customer_id in ((select id from customer where name like :search) union (select id from customer where phone_number like :search)))) and order_status_id = 2 and merchant_id = :id", nativeQuery = true)
    Iterable<Order> merchantSearchDenied(String search, Long id);

    @Query(value = "select * from orders where (id like :search or (customer_id in ((select id from customer where name like :search) union (select id from customer where phone_number like :search)))) and order_status_id = 3 and merchant_id = :id", nativeQuery = true)
    Iterable<Order> merchantSearchWait(String search, Long id);

    @Query(value = "select sum(price_total) as total from orders where merchant_id = :merchant_id and create_at between :from and :to group by date(create_at) limit 7", nativeQuery = true)
    Iterable<Double> getReveneuOfMerchant(Long merchant_id, String from, String to);
}