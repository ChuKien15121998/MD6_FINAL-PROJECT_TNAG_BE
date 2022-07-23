package com.codegym.controller;

import com.codegym.dto.response.ResponseMessage;
import com.codegym.model.*;
import com.codegym.security.userpincal.UserDetailService;
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
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private IFoodService foodService;
    @Autowired
    private CartDetailService cartDetailService;
    @Autowired
    private ICartService cartService;


    @PostMapping("/{idFood}")
    public ResponseEntity<?> addToCart (@PathVariable Long idFood) {
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
        Optional<Food> foodOptional = foodService.findById(idFood);
        if (!foodOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Food food = foodOptional.get();
        Optional<CartDetail> cartDetailOptional = cartDetailService.findCartDetailByCartAndFood(cart, food);
        CartDetail cartDetail;
        if (cartDetailOptional.isPresent()) {
            cartDetail = cartDetailOptional.get();
            cartDetail.setQuantity(cartDetail.getQuantity() + 1);
        } else {
            cartDetail = new CartDetail();
            cartDetail.setCart(cart);
            cartDetail.setFood(food);
            cartDetail.setQuantity(1);
            cartDetail.setTotalPrice(cartDetail.getFood().getPrice() * cartDetail.getQuantity());

        }
        cartDetailService.save(cartDetail);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @PutMapping ("/decrease/{idFood}")
    public ResponseEntity<?> decreaseQuantity(@PathVariable Long idFood) {
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
        Optional<Food> foodOptional = foodService.findById(idFood);
        if (!foodOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Food food = foodOptional.get();
        Optional<CartDetail> cartDetailOptional = cartDetailService.findCartDetailByCartAndFood(cart, food);
        CartDetail cartDetail;
        if (cartDetailOptional.isPresent()) {
            cartDetail = cartDetailOptional.get();
            if (cartDetail.getQuantity() == 1) {
                cartDetailService.remove(cartDetail.getId());
            } else {
                cartDetail.setQuantity(cartDetail.getQuantity() - 1);
                cartDetail.setTotalPrice(cartDetail.getQuantity() * cartDetail.getFood().getPrice());
                cartDetailService.save(cartDetail);
            }
        }
        return new ResponseEntity<>(new ResponseMessage("decrease success"), HttpStatus.OK);
    }

    @PutMapping ("/increase/{idFood}")
    public ResponseEntity<?> increaseQuantity(@PathVariable Long idFood) {
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
        Optional<Food> foodOptional = foodService.findById(idFood);
        if (!foodOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Food food = foodOptional.get();
        Optional<CartDetail> cartDetailOptional = cartDetailService.findCartDetailByCartAndFood(cart, food);
        CartDetail cartDetail;
        if (cartDetailOptional.isPresent()) {
            cartDetail = cartDetailOptional.get();
            cartDetail.setQuantity(cartDetail.getQuantity() + 1);
            cartDetail.setTotalPrice(cartDetail.getQuantity() * cartDetail.getFood().getPrice());
            cartDetailService.save(cartDetail);
            }
        return new ResponseEntity<>(new ResponseMessage("increase success"), HttpStatus.OK);
    }
}
