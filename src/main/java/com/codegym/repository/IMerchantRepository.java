package com.codegym.repository;

import com.codegym.model.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMerchantRepository extends JpaRepository<Merchant, Long> {
    Page<Merchant> findAll(Pageable pageable);
}
