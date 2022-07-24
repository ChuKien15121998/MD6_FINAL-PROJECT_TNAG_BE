package com.codegym.controller;

import com.codegym.model.Order;
import com.codegym.model.OrderDetails;
import com.codegym.service.IOrderDetailService;
import com.codegym.service.IOrderSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/orderdetails")
public class OrderDetailsController {

    @Autowired
    IOrderSevice orderSevice;

    @Autowired
    IOrderDetailService orderDetailService;

    @GetMapping("/{order_id}")
    ResponseEntity<?> findAllByOrder(@PathVariable Long order_id) {
        Optional<Order> orderOptional = orderSevice.findById(order_id);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Order order = orderOptional.get();
        Iterable<OrderDetails> orderDetails = orderDetailService.findAllByOrder(order);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }
}
