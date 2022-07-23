package com.codegym.repository;

import com.codegym.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {
}
