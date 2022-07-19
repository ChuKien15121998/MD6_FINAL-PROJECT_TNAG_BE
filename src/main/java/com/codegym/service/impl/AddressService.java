package com.codegym.service.impl;

import com.codegym.model.Address;
import com.codegym.repository.IAddressRepository;
import com.codegym.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AddressService implements IAddressService {
    @Autowired
    IAddressRepository addressRepository;
    public Iterable<Address> findAll() {
        return addressRepository.findAll();
    }

    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public void remove(Long id) {
        addressRepository.deleteById(id);
    }
}
