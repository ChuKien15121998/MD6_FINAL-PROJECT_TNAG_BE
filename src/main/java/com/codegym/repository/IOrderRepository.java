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

    Iterable<Order> findAllByCustomerOrderByCreateAt(Customer customer);

    Iterable<Order> findAllByOrderStatus(OrderStatus orderStatus);

    @Query(value = "select month(create_at), sum(price_total) from orders where merchant_id = :id and year(create_at) = :year group by month(create_at) order by month(create_at) asc", nativeQuery = true)
    Iterable<Order> findAllByPriceTotalOrderByCreateAt(Long id, int year);

}