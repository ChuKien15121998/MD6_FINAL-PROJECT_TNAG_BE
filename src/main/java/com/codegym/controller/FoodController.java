package com.codegym.controller;

import com.codegym.model.*;
import com.codegym.security.userpincal.UserDetailService;
import com.codegym.service.impl.FoodService;
import com.codegym.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    FoodService foodService;
    @Autowired
    IMerchantService merchantService;

    @Autowired
    UserDetailService userDetailService;

    //Show list
    @GetMapping
    public ResponseEntity<Iterable<Food>> findAll() {
        Iterable<Food> foodList = foodService.findAll();
        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }


    //Show list theo merchant
    @GetMapping("/{merchant_id}")
    public ResponseEntity<Iterable<Food>> findAllById(@PathVariable Long merchant_id, Pageable pageable) {
        return new ResponseEntity<>(foodService.findAllByMerchantId(merchant_id, pageable), HttpStatus.OK);
    }

    @GetMapping("/merchant")
    public ResponseEntity<?> listFoodByMerchant() {
        AppUser appUser = userDetailService.getCurrentUser();
        Optional<Merchant> merchant = merchantService.findMerchantByAppUser(appUser);
        if (!merchant.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<Food> foods = foodService.findAllByMerchant(merchant.get());
        if (foods != null) {
            return new ResponseEntity<>(foods, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Search by id
    @GetMapping("/food-id/{food_id}")
    public ResponseEntity<Food> finById(@PathVariable Long food_id) {
        return new ResponseEntity<>(foodService.findById(food_id).get(), HttpStatus.OK);
    }

    //Search by name
    @GetMapping("/search-by-food-name")
    public ResponseEntity<Iterable<Food>> findAllByNameContaining(@RequestParam String name, @PageableDefault Pageable pageable) {
        return new ResponseEntity<>(foodService.findAllByNameContaining(pageable, name), HttpStatus.OK);
    }

    //Create food
    @PostMapping("/{merchant_id}")
    public ResponseEntity add(@RequestBody Food food, @PathVariable Long merchant_id) {
        food.setMerchant(merchantService.findById(merchant_id).get());
        foodService.save(food);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Delete food
    @PutMapping("/delete-food/{food_id}")
    public ResponseEntity<Food> delete(@PathVariable Long food_id) {
        foodService.findById(food_id).get().setDelete(false);
        return new ResponseEntity<>(foodService.findById(food_id).get(), HttpStatus.OK);
    }

    //Update food
    @PutMapping("/update-food/{food_id}")
    public ResponseEntity<Food> edit(@RequestBody Food food, @PathVariable Long food_id) {
        food.setId(food_id);
        food.setDelete(true);
        foodService.save(food);
        return new ResponseEntity<>(foodService.findById(food_id).get(), HttpStatus.OK);
    }


}