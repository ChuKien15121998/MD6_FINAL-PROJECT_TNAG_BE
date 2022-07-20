package com.codegym.service;

import com.codegym.model.Address;
import com.codegym.model.Customer;

public interface IAddressService extends IGeneralService<Address> {
    Iterable<Address> findAddressByCustomer (Customer customer);
    Boolean existsByNameAddress(String nameAddress);

}
