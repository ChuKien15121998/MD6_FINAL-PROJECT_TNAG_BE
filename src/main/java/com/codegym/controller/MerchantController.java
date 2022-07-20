package com.codegym.controller;

import com.codegym.model.Merchant;
import com.codegym.service.IMerchantService;
import jdk.vm.ci.meta.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<?> showListMerchant(@PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
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

    @GetMapping("/change-status/{id}/{status}")
    public ResponseEntity<Merchant> updateStatusMerchant(@PathVariable Long id, @PathVariable Long status) {
        Optional<Merchant> merchantOptional = merchantService.findById(id);
        if (!merchantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Merchant newMerchant = merchantOptional.get();
        newMerchant.setActive(status == 1 ? true : false);
        return new ResponseEntity<>(merchantService.save(newMerchant), HttpStatus.OK);
    }

    @PutMapping("/change-gold-status/{id}")
    public ResponseEntity<Merchant> changeGoldParterStatus(@PathVariable Long id, @RequestBody Merchant merchant) {
        Optional<Merchant> merchantOptional = merchantService.findById(id);
        if (!merchantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Merchant newMerchant = merchantOptional.get();
        newMerchant.setId(id);
        newMerchant.setGoldPartner(merchant.getGoldPartner());
        return new ResponseEntity<>(merchantService.save(newMerchant), HttpStatus.OK);
    }
}
