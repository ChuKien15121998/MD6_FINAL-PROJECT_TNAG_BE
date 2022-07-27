package com.codegym.repository;

import com.codegym.model.AddressCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressCategoryRepository extends JpaRepository<AddressCategory, Long> {
}
