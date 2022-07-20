package com.codegym.service.impl;

import com.codegym.model.AppUser;
import com.codegym.model.Customer;
import com.codegym.repository.ICustomerRepository;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepository customerRepository;

    public Optional<Customer> findCustomerByAppUser_Id(Long id) {
        return customerRepository.findCustomerByAppUser_Id(id);
    }

    public Optional<Customer> findCustomerByAppUser(AppUser appUser) {
        return customerRepository.findCustomerByAppUser(appUser);
    }

    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public void remove(Long id) {
        customerRepository.deleteById(id);
    }
}
