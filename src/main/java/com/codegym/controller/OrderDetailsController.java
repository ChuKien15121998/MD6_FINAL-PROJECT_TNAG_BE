package com.codegym.controller;

import com.codegym.model.Food;
import com.codegym.model.Order;
import com.codegym.model.OrderDetails;
import com.codegym.service.IFoodService;
import com.codegym.service.IOrderDetailService;
import com.codegym.service.IOrderService;
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
    IOrderService orderService;

    @Autowired
    IOrderDetailService orderDetailService;

    @Autowired
    IFoodService foodService;

    @GetMapping("/order/{order_id}")
    ResponseEntity<?> findAllByOrder(@PathVariable Long order_id) {
        Optional<Order> orderOptional = orderService.findById(order_id);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Order order = orderOptional.get();
        Iterable<OrderDetails> orderDetails = orderDetailService.findAllByOrder(order);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @GetMapping("/food/{foodId}")
    ResponseEntity<?> findAllByFood(@PathVariable Long foodId) {
        Optional<Food> foodOptional = foodService.findById(foodId);
        if (!foodOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Food food = foodOptional.get();
        Iterable<OrderDetails> orderDetails = orderDetailService.findAllByFood(food);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }
}
