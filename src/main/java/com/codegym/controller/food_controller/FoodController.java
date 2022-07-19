package com.codegym.controller.food_controller;


import com.codegym.model.Food;
import com.codegym.service.food_service.FoodService;
import com.codegym.service.merchant_service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    FoodService foodService;
    MerchantService merchantService;

    @GetMapping
    public ResponseEntity<Iterable<Food>> findAll() {
        Iterable<Food> foodList = foodService.findAll();
        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    @GetMapping("/{merchant_id}")
    public ResponseEntity<Iterable<Food>> findAllById(@PathVariable Long merchant_id, Pageable pageable) {
        return new ResponseEntity<>(foodService.findAllByMerchantId(merchant_id, pageable), HttpStatus.OK);
    }
}
