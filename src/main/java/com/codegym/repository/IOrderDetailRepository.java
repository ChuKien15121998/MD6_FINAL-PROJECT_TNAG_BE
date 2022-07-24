package com.codegym.repository;

import com.codegym.model.Order;
import com.codegym.model.OrderDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetails, Long> {
    Iterable<OrderDetails> findAllByOrder(Order order);

    Iterable<OrderDetails> findAllByFoodId(Long id);
}
