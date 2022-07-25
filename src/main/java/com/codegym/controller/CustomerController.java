package com.codegym.controller;

import com.codegym.dto.request.ChangeAvatar;
import com.codegym.dto.request.ChangeProfileCustomForm;
import com.codegym.dto.response.ResponseMessage;
import com.codegym.model.AppUser;
import com.codegym.model.Customer;
import com.codegym.model.Merchant;
import com.codegym.security.jwt.JwtProvider;
import com.codegym.security.jwt.JwtTokenFilter;
import com.codegym.security.userpincal.UserDetailService;
import com.codegym.service.IUserService;
import com.codegym.service.impl.CustomerService;
import com.codegym.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    IUserService userService;

    @Autowired
    CustomerService customerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailService userDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<?> detailCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @GetMapping("/detail")
    public ResponseEntity<?> getCurrentCustomer() {
        AppUser appUser = userDetailService.getCurrentUser();
        Optional<Customer> customer = customerService.findCustomerByAppUser(appUser);
        return new ResponseEntity<>(customer, HttpStatus.OK);
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
                Optional<Customer> customer = customerService.findCustomerByAppUser(appUser.get());
                customer.get().setAvatar(changeAvatar.getAvatar());
                customerService.save(customer.get());
            }
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
        } catch (UsernameNotFoundException exception) {
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/change-profile")
    public ResponseEntity<?> changeProfile(HttpServletRequest request, @Valid @RequestBody ChangeProfileCustomForm changeProfileCustomForm) {
        String jwt = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUserNameFromToken(jwt);
        try {
            if (changeProfileCustomForm.getName() == null) {
                return new ResponseEntity<>(new ResponseMessage("no_name"), HttpStatus.OK);
            } else if (changeProfileCustomForm.getPhoneNumber() == null){
                return new ResponseEntity<>(new ResponseMessage("no_phone"), HttpStatus.OK);
            } else {
                Optional<AppUser> appUser = userService.findByUsername(username);
                if (!appUser.isPresent()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                Optional<Customer> customer = customerService.findCustomerByAppUser(appUser.get());
                customer.get().setName(changeProfileCustomForm.getName());
                customer.get().setPhoneNumber(changeProfileCustomForm.getPhoneNumber());
                customerService.save(customer.get());
            }
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
        } catch (UsernameNotFoundException exception) {
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
