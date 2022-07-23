package com.codegym.service;
import com.codegym.model.Cart;
import com.codegym.model.Customer;

import java.util.Optional;

public interface ICartService extends IGeneralService<Cart>{
    Optional<Cart> findCartByCustomer(Customer customer);

}
