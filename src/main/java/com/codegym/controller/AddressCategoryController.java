package com.codegym.controller;

import com.codegym.model.AddressCategory;
import com.codegym.service.impl.AddressCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("address-category")
public class AddressCategoryController {
    @Autowired
    AddressCategoryService addressCategoryService;

    @GetMapping
    public ResponseEntity<?> listAddressCategory() {
        Iterable<AddressCategory> addressCategories = addressCategoryService.findAll();
        if (addressCategories != null) {
            return new ResponseEntity<>(addressCategories, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
