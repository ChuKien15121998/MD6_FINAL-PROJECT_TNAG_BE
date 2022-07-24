package com.codegym.controller;

import com.codegym.model.*;
import com.codegym.repository.IMerchantRepository;
import com.codegym.repository.IOrderRepository;
import com.codegym.security.userpincal.UserDetailService;
import com.codegym.service.*;
import com.codegym.service.impl.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    IMerchantService merchantService;

    @Autowired
    UserDetailService userDetailsService;

    @Autowired
    IOrderSevice orderSevice;

    @Autowired
    ICustomerService customerService;

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    IOrderDetailService orderDetailService;

    @Autowired
    IFoodService foodService;

    @GetMapping("/merchant-order")
    ResponseEntity<?> findAllOrderOfMerchant() {
        AppUser appUser = userDetailsService.getCurrentUser();
        Optional<Merchant> merchantOptional = merchantService.findMerchantByAppUser(appUser);
        if (!merchantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Merchant merchant = merchantOptional.get();
        Iterable<Order> ordersOfMerchant = orderSevice.findAllByMerchant(merchant);
        return new ResponseEntity<>(ordersOfMerchant, HttpStatus.OK);
    }

    @GetMapping("/customer-order")
    ResponseEntity<?> findAllOrderOfCustomer() {
        AppUser appUser = userDetailsService.getCurrentUser();
        Optional<Customer> customerOptional = customerService.findCustomerByAppUser(appUser);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer customer = customerOptional.get();
        Iterable<Order> ordersOfCustomer = orderSevice.findAllByCustomer(customer);
        return new ResponseEntity<>(ordersOfCustomer, HttpStatus.OK);
    }

    @PostMapping("/{orderId}/accept")
    ResponseEntity<?> merchantAcceptOrder(@PathVariable Long orderId) {
        AppUser appUser = userDetailsService.getCurrentUser();
        Optional<Merchant> merchantOptional = merchantService.findMerchantByAppUser(appUser);
        if (!merchantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Merchant merchant = merchantOptional.get();
        Optional<Order> orderOptional = orderSevice.findById(orderId);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Order order = orderOptional.get();
        if (order.getMerchant() != merchant) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        order.setOrderStatus(new OrderStatus("ACCEPTED"));
        Iterable<OrderDetails> orderDetailsList = orderDetailService.findAllByOrder(order);
//        List<Food> foodList = new ArrayList<>();
        for (OrderDetails orderDetails: orderDetailsList) {
            Food food = orderDetails.getFood();
            food.setSold((long) (food.getSold()+orderDetails.getQuantity()));
            foodService.save(food);
        }
        order = orderSevice.save(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/{orderId}/deny")
    ResponseEntity<?> merchantDenyOrder(@PathVariable Long orderId){
        AppUser appUser = userDetailsService.getCurrentUser();
        Optional<Merchant> merchantOptional = merchantService.findMerchantByAppUser(appUser);
        if (!merchantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Merchant merchant = merchantOptional.get();
        Optional<Order> orderOptional = orderSevice.findById(orderId);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Order order = orderOptional.get();
        if (order.getMerchant() != merchant) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        order.setOrderStatus(new OrderStatus("CANCELED"));
        order = orderSevice.save(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


}
