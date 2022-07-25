package com.codegym.service;

import com.codegym.model.AppUser;
import com.codegym.model.Customer;

import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {
    Optional<Customer> findCustomerByAppUser (AppUser appUser);

}
