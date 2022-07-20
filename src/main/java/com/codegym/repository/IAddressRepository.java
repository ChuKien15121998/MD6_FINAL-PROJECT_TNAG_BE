package com.codegym.repository;

import com.codegym.model.Address;
import com.codegym.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {
    Boolean existsByNameAddress(String nameAddress);

    Iterable<Address> findAddressByCustomer (Customer customer);
}
