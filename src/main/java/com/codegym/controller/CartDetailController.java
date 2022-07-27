package com.codegym.controller;

import com.codegym.model.*;
import com.codegym.security.userpincal.UserDetailService;
import com.codegym.service.ICartDetailService;
import com.codegym.service.ICartService;
import com.codegym.service.ICustomerService;
import com.codegym.service.IFoodService;
import com.codegym.service.impl.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/cartdetails")
public class CartDetailController {
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private IFoodService foodService;
    @Autowired
    private CartDetailService cartDetailService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICartService cartService;
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCartDetailById (@PathVariable Long id) {
        cartDetailService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> listCartDetailById (@PathVariable Long id) {
        Optional<CartDetail> cartDetailOptional = cartDetailService.findById(id);
        if (!cartDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartDetailOptional.get(), HttpStatus.OK);
    }
    @GetMapping("/cart/{food_id}")
    public ResponseEntity<?> findCartDetailByCartAndFood(@PathVariable Long food_id){
        AppUser appUser = userDetailService.getCurrentUser();
        Optional<Customer> customerOptional = customerService.findCustomerByAppUser(appUser);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer customer = customerOptional.get();
        Optional<Cart> cartOptional = cartService.findCartByCustomer(customer);
        if (!cartOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Cart cart = cartOptional.get();
        Optional<Food> foodOptional = foodService.findById(food_id);
        if (!foodOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Food food = foodOptional.get();
        Optional<CartDetail> cartDetailOptional = cartDetailService.findCartDetailByCartAndFood(cart, food);
        return new ResponseEntity<>(cartDetailOptional.get(), HttpStatus.OK);
    }
}
