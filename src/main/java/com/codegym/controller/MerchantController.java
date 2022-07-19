package com.codegym.controller;

import com.codegym.model.Merchant;
import com.codegym.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/merchants")
public class MerchantController {
    @Autowired
    private IMerchantService merchantService;
    @GetMapping
    public ResponseEntity<?> showListMerchant(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Merchant> merchants = merchantService.findAll(pageable);
        if (merchants.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(merchants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showMerchantDetail(@PathVariable long id) {
        Optional<Merchant> merchantOptional = merchantService.findById(id);
        if (!merchantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(merchantOptional.get(), HttpStatus.OK);
    }
}
