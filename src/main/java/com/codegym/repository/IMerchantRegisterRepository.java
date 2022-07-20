package com.codegym.repository;

import com.codegym.model.MerchantRegisterRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMerchantRegisterRepository extends JpaRepository<MerchantRegisterRequest, Long> {
    Optional<MerchantRegisterRequest> findMerchantRegisterRequestByUsernameAndReviewed (String name, boolean reviewed);

    Iterable<MerchantRegisterRequest> findMerchantByReviewed(boolean reviewed);
}
