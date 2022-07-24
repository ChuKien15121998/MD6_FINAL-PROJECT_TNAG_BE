package com.codegym.controller;

import com.codegym.model.*;
import com.codegym.repository.IMerchantRepository;
import com.codegym.repository.IOrderRepository;
import com.codegym.security.userpincal.UserDetailService;
import com.codegym.service.*;
import com.codegym.service.impl.CartService;
import com.codegym.service.impl.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    IOrderStatusService orderStatusService;
    @Autowired
    ICartDetailService cartDetailService;
    @Autowired
    ICartService cartService;

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

    @PutMapping("/{orderId}/accept")
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
        order.setOrderStatus(orderStatusService.findByNameOrderStatus("ACCEPTED").get());
        Iterable<OrderDetails> orderDetailsList = orderDetailService.findAllByOrder(order);
//        List<Food> foodList = new ArrayList<>();
        for (OrderDetails orderDetails : orderDetailsList) {
            Food food = orderDetails.getFood();
            food.setSold((long) (food.getSold() + orderDetails.getQuantity()));
            foodService.save(food);
        }
        order = orderSevice.save(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/deny")
    ResponseEntity<?> merchantDenyOrder(@PathVariable Long orderId) {
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
        order.setOrderStatus(orderStatusService.findByNameOrderStatus("DENIED").get());
        return new ResponseEntity<>(orderSevice.save(order), HttpStatus.OK);
    }

    @PostMapping("/createOrder/{cartId}/{merchantId}")
    ResponseEntity<?> createOrder(@PathVariable Long cartId, @PathVariable Long merchantId) {
        AppUser appUser = userDetailsService.getCurrentUser();
        Optional<Customer> customerOptional = customerService.findCustomerByAppUser(appUser);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer customer = customerOptional.get();
        Cart cart = cartService.findById(cartId).get();
        Merchant merchant = merchantService.findById(merchantId).get();
        Iterable<CartDetail> cartDetails = cartDetailService.findAllByCartAndMerchant(cart, merchant);
        double totalOrderPrice = 0;
        Order order = new Order();
        order.setOrderStatus(orderStatusService.findByNameOrderStatus("WAIT").get());
        order.setCreateAt(new Date());
        order.setCustomer(customer);
        order.setMerchant(merchant);
        orderSevice.save(order);
        for (CartDetail cartDetail: cartDetails) {
            totalOrderPrice += cartDetail.getTotalPrice();
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrder(order);
            orderDetails.setFood(cartDetail.getFood());
            orderDetails.setQuantity(cartDetail.getQuantity());
            orderDetails.setPrice(cartDetail.getTotalPrice());
            orderDetailService.save(orderDetails);
        }
        order.setPriceTotal(totalOrderPrice);
        orderSevice.save(order);
//        cartDetailService.deleteAllByCartAndMerchant(cart, merchant);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
