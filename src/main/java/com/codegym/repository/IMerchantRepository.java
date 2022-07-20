package com.codegym.repository;

import com.codegym.model.AppUser;
import com.codegym.model.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMerchantRepository extends JpaRepository<Merchant, Long> {
    Page<Merchant> findAll(Pageable pageable);

    Optional<Merchant> findMerchantByAppUser_Id(Long id);

    Optional<Merchant> findMerchantByAppUser(AppUser appUser);

    Iterable<Merchant> findMerchantByNameContaining(String name);
}
