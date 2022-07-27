package com.codegym.controller;

import com.codegym.dto.response.ResponseMessage;
import com.codegym.model.*;
import com.codegym.security.jwt.JwtProvider;
import com.codegym.security.jwt.JwtTokenFilter;
import com.codegym.security.userpincal.UserDetailService;
import com.codegym.service.IUserService;
import com.codegym.service.impl.FoodService;
import com.codegym.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @Autowired
    FoodService foodService;
    @Autowired
    IMerchantService merchantService;

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    IUserService iUserService;

    //Show list
    @GetMapping
    public ResponseEntity<Iterable<Food>> findAll() {
        Iterable<Food> foodList = foodService.findAll();
        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    //Show list where delete false
    @GetMapping("/show-food-by-user")
    public ResponseEntity<Iterable<Food>> findAllByUser() {
        Iterable<Food> foodList = foodService.findAllByUser();
        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    //Show list food by category
    @GetMapping("/category/{category_id}")
    public ResponseEntity<Iterable<Food>> findAllByCategory(@PathVariable Long category_id) {
        Iterable<Food> foods = foodService.showListFoodByCategory(category_id);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    //get merchant by foodId
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
        Iterable<Food> foods = foodService.findAllByMerchantAndIsDeleteTrue(merchant.get());
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
    @GetMapping("/{id}/search-by-food-name")
    public ResponseEntity<Iterable<Food>> findAllByNameContaining(@PathVariable Long id, @RequestParam String name, @PageableDefault Pageable pageable) {
        return new ResponseEntity<>(foodService.findAllByNameContaining('%'+name+'%', id,pageable),HttpStatus.OK);
    }

    @GetMapping("/search-all-food-by-name")
    public ResponseEntity<Iterable<Food>> findAllByNameByUser(@RequestParam String name) {
        return new ResponseEntity<>(foodService.findByNameFoodByUser('%' + name + '%'), HttpStatus.OK);
    }

    //Create food
    @PostMapping("/{merchant_id}")
    public ResponseEntity add(@RequestBody Food food, @PathVariable Long merchant_id) {
        food.setMerchant(merchantService.findById(merchant_id).get());
        food.setDelete(true);
        if (food.getImage().equals("")) {
            food.setImage("https://firebasestorage.googleapis.com/v0/b/fir-470c3.appspot.com/o/z3578349206972_f4eb9fa8a74840c95635b0f81642e08d.jpg?alt=media&token=02cfb780-a065-4f28-819e-8b1d5a432be5");
        }
        food.setRecommend(false);
        food.setSold(0L);
        foodService.save(food);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createFood(@RequestBody Food food) {
        AppUser appUser = userDetailService.getCurrentUser();
        Optional<Merchant> merchant = merchantService.findMerchantByAppUser(appUser);
        food.setMerchant(merchant.get());
        food.setDelete(true);
        if (food.getImage().equals("")) {
            food.setImage("https://firebasestorage.googleapis.com/v0/b/fir-470c3.appspot.com/o/z3578349206972_f4eb9fa8a74840c95635b0f81642e08d.jpg?alt=media&token=02cfb780-a065-4f28-819e-8b1d5a432be5");
        }
        food.setRecommend(false);
        food.setSold(0L);
        foodService.save(food);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    //Delete food
    @PutMapping("/delete-food/{food_id}")
    public ResponseEntity<Food> delete(@PathVariable Long food_id) {
        Optional<Food> food = foodService.findById(food_id);
        food.get().setDelete(false);
        return new ResponseEntity<>(foodService.save(food.get()), HttpStatus.OK);
    }

    //Update food
    @PutMapping("/update-food/{food_id}")
    public ResponseEntity<?> edit(@RequestBody Food food, @PathVariable Long food_id, HttpServletRequest httpServletRequest) {
        String token = jwtTokenFilter.getJwt(httpServletRequest);
        String userName = jwtProvider.getUserNameFromToken(token);
        Optional<AppUser> appUserOptional = iUserService.findByUsername(userName);
        if (!appUserOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Food> foodOptional = foodService.findById(food_id);
        if (foodOptional.get().getMerchant().getAppUser() != appUserOptional.get()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        food.setId(food_id);
        food.setDelete(true);
        food.setMerchant(foodOptional.get().getMerchant());
        food.setRecommend(false);
        food.setSold(foodOptional.get().getSold());
        if (food.getImage().equals("")){
            food.setImage(foodOptional.get().getImage());
        }
        foodService.save(food);
        return new ResponseEntity<>(foodService.save(food), HttpStatus.OK);
    }

    @GetMapping("/best-seller")
    public ResponseEntity<?> listSoldTop8() {
        Iterable<Food> foods = foodService.listSoldTop8();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/newest")
    public ResponseEntity<?> listNewestFood() {
        Iterable<Food> foods = foodService.listNewestFood();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
