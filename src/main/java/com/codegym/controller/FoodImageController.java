package com.codegym.controller;

import com.codegym.model.AppUser;
import com.codegym.model.Customer;
import com.codegym.model.Food;
import com.codegym.model.FoodImage;
import com.codegym.service.IFoodImageService;
import com.codegym.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/foodimage")
public class FoodImageController {
    @Autowired
    IFoodImageService foodImageService;

    @Autowired
    IFoodService foodService;

    @GetMapping("/{foodId}")
    public ResponseEntity<?> getListImage(@PathVariable Long foodId) {
        Optional<Food> foodOptional = foodService.findById(foodId);
        if (!foodOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Food food = foodOptional.get();
        Iterable<FoodImage> foodImages = foodImageService.findFoodImageByFood(food);
        return new ResponseEntity<>(foodImages, HttpStatus.OK);
    }
}
