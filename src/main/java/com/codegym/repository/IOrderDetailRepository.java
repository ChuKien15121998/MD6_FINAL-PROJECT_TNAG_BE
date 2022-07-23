package com.codegym.repository;

import com.codegym.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailRepository extends JpaRepository<OrderDetails, Long> {
}
