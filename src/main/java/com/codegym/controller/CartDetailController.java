package com.codegym.controller;

import com.codegym.model.CartDetail;
import com.codegym.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/cartdetails")
public class CartDetailController {
    @Autowired
    ICartDetailService cartDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<?> listCartDetailById (@PathVariable Long id) {
        Optional<CartDetail> cartDetailOptional = cartDetailService.findById(id);
        if (!cartDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartDetailOptional.get(), HttpStatus.OK);
    }
}
