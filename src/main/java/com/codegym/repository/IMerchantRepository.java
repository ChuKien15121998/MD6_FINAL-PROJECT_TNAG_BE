package com.codegym.repository;

import com.codegym.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMerchantRepository extends JpaRepository<Merchant, Long> {
    Optional<Merchant> findMerchantByAppUser_Id(Long id);

    Iterable<Merchant> findMerchantByNameContaining(String name);
}
