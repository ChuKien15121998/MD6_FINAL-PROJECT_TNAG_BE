package com.codegym.controller;

import com.codegym.dto.request.ChangeAvatar;
import com.codegym.dto.request.ChangeProfileCustomForm;
import com.codegym.dto.request.ChangeProfileMerchant;
import com.codegym.dto.response.ResponseMessage;
import com.codegym.model.AppUser;
import com.codegym.model.Customer;
import com.codegym.model.Merchant;
import com.codegym.security.jwt.JwtProvider;
import com.codegym.security.jwt.JwtTokenFilter;
import com.codegym.service.IMerchantService;
import com.codegym.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/merchants")
public class MerchantController {
    @Autowired
    private IMerchantService merchantService;
    @Autowired
    IUserService userService;
    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @Autowired
    JwtProvider jwtProvider;
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
    public ResponseEntity<?> showMerchantDetail(@PathVariable Long id) {
        Optional<Merchant> merchantOptional = merchantService.findById(id);
        if (!merchantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(merchantOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/change-avatar")
    public ResponseEntity<?> changeAvatar(HttpServletRequest request, @Valid @RequestBody ChangeAvatar changeAvatar) {
        String jwt = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUserNameFromToken(jwt);
        try {
            if (changeAvatar.getAvatar() == null) {
                return new ResponseEntity<>(new ResponseMessage("no"), HttpStatus.OK);
            } else {
                Optional<AppUser> appUser = userService.findByUsername(username);
                if (!appUser.isPresent()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                Optional<Merchant> merchant = merchantService.findMerchantByAppUser(appUser.get());
                merchant.get().setAvatar(changeAvatar.getAvatar());
                merchantService.save(merchant.get());
            }
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
        } catch (UsernameNotFoundException exception) {
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/change-banner")
    public ResponseEntity<?> changeBanner(HttpServletRequest request, @Valid @RequestBody ChangeAvatar changeAvatar) {
        String jwt = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUserNameFromToken(jwt);
        try {
            if (changeAvatar.getAvatar() == null) {
                return new ResponseEntity<>(new ResponseMessage("no"), HttpStatus.OK);
            } else {
                Optional<AppUser> appUser = userService.findByUsername(username);
                if (!appUser.isPresent()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                Optional<Merchant> merchant = merchantService.findMerchantByAppUser(appUser.get());
                merchant.get().setImageBanner(changeAvatar.getAvatar());
                merchantService.save(merchant.get());
            }
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
        } catch (UsernameNotFoundException exception) {
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editMerchant(@PathVariable Long id, @RequestBody Merchant merchant) {
        Optional<Merchant> merchantOptional = merchantService.findById(id);
        if (!merchantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        merchant.setId(merchantOptional.get().getId());
        merchant.setPhoneNumber(merchantOptional.get().getPhoneNumber());
        merchant.setGoldPartner(merchantOptional.get().isGoldPartner());
        merchant.setAppUser(merchantOptional.get().getAppUser());
        merchant.setActive(merchantOptional.get().isActive());
        merchant.setAccept(merchantOptional.get().isAccept());
        merchantService.save(merchant);
        return new ResponseEntity<>(new ResponseMessage("update success"), HttpStatus.OK);
    }

    @PutMapping("/change-profile")
    public ResponseEntity<?> changeProfile(HttpServletRequest request, @Valid @RequestBody ChangeProfileMerchant changeProfileMerchant) {
        String jwt = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUserNameFromToken(jwt);
        try {
            if (changeProfileMerchant.getName() == null) {
                return new ResponseEntity<>(new ResponseMessage("no_name"), HttpStatus.OK);
            } else if (changeProfileMerchant.getOpenTime() == null){
                return new ResponseEntity<>(new ResponseMessage("no_openTime"), HttpStatus.OK);
            } else if (changeProfileMerchant.getCloseTime() == null){
                return new ResponseEntity<>(new ResponseMessage("no_closeTime"), HttpStatus.OK);
            } else if (changeProfileMerchant.getAddress() == null){
                return new ResponseEntity<>(new ResponseMessage("no_address"), HttpStatus.OK);
            } else {
                Optional<AppUser> appUser = userService.findByUsername(username);
                if (!appUser.isPresent()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                Optional<Merchant> merchant = merchantService.findMerchantByAppUser(appUser.get());
                merchant.get().setName(changeProfileMerchant.getName());
                merchant.get().setOpenTime(changeProfileMerchant.getOpenTime());
                merchant.get().setCloseTime(changeProfileMerchant.getCloseTime());
                merchant.get().setAddress(changeProfileMerchant.getAddress());
                merchantService.save(merchant.get());
            }
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
        } catch (UsernameNotFoundException exception) {
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
