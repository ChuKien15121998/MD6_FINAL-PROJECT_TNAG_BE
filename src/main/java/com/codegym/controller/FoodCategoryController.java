package com.codegym.controller;


import com.codegym.dto.response.ResponseMessage;
import com.codegym.model.Food;

import com.codegym.model.FoodCategory;
import com.codegym.service.IFoodCategoryService;
import com.codegym.service.IMerchantService;
import com.codegym.service.food_service.FoodService;


import com.codegym.service.impl.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/food-categories")
public class FoodCategoryController {

    @Autowired
    IFoodCategoryService foodCategoryService;


    //Show list
    @GetMapping
    public ResponseEntity<Iterable<FoodCategory>> findAll() {
        Iterable<FoodCategory> foodCategories = foodCategoryService.findAll();
        return new ResponseEntity<>(foodCategories, HttpStatus.OK);
    }


    //Search by id
    @GetMapping("/{id}")
    public ResponseEntity<FoodCategory> finById(@PathVariable Long id) {
        return new ResponseEntity<>(foodCategoryService.findById(id).get(), HttpStatus.OK);
    }


    //Create food category
    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody FoodCategory foodCategory) {
        foodCategoryService.save(foodCategory);
        return new ResponseEntity<>(new ResponseMessage("Successfully!"), HttpStatus.OK);
    }


}
