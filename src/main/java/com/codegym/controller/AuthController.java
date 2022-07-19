package com.codegym.controller;

import com.codegym.dto.request.SignInForm;
import com.codegym.dto.request.SignUpFormCustomer;
import com.codegym.dto.request.SignUpFormMerchant;
import com.codegym.dto.response.JwtResponse;
import com.codegym.dto.response.ResponseMessage;
import com.codegym.model.*;
import com.codegym.security.jwt.JwtProvider;
import com.codegym.security.userpincal.UserPrinciple;
import com.codegym.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    CustomerService customerService;

    @Autowired
    AddressService addressService;
    @Autowired
    MerchantRegisterRequestService merchantRegisterRequestService;
    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("customer/signup")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody SignUpFormCustomer signUpFormCustomer) {
        if (!signUpFormCustomer.getPassword().equals(signUpFormCustomer.getConfirmPassword())) {
            return new ResponseEntity<>(new ResponseMessage("no_password"), HttpStatus.OK);
        }
        if (userService.existsByUsername(signUpFormCustomer.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("no_user"), HttpStatus.OK);
        }
        if (signUpFormCustomer.getAvatar() == null || signUpFormCustomer.getAvatar().trim().isEmpty()) {
            signUpFormCustomer.setAvatar("https://firebasestorage.googleapis.com/v0/b/blog-firebase-c1eff.appspot.com/o/images%2F765-default-avatar.png?alt=media&token=913a079e-dbff-4ff1-a15b-be184446f58b");
        }
        AppUser appUser = new AppUser(signUpFormCustomer.getUsername(), passwordEncoder.encode(signUpFormCustomer.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleService.findByName(RoleName.USER).orElseThrow(() -> new RuntimeException("Role not found"));
        roles.add(userRole);
        appUser.setRoles(roles);
        userService.save(appUser);
        Customer customer = new Customer(signUpFormCustomer.getName(),signUpFormCustomer.getAvatar(), signUpFormCustomer.getPhone(), appUser);
        customerService.save(customer);
        AddressCategory addressCategory = signUpFormCustomer.getAddressCategory();
        Address address = new Address(signUpFormCustomer.getAddress(), addressCategory, customer);
        addressService.save(address);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @PostMapping("customer/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword())
        );
        // Thêm đối tượng này vào security để xử lý tiếp
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Khởi tạo jwt từ đối tượng này
        String token = jwtProvider.createToken(authentication);
        // Tạo đối tượng userprinciple từ authentication.getPrincipal
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getUsername(), userPrinciple.getAuthorities()));
    }

    @PostMapping("merchant/signup")
    public ResponseEntity<?> registerMerchant(@Valid @RequestBody SignUpFormMerchant signUpFormMerchant) {
        if (!signUpFormMerchant.getPassword().equals(signUpFormMerchant.getConfirmPassword())) {
            return new ResponseEntity<>(new ResponseMessage("no_password"), HttpStatus.OK);
        }
        Optional<MerchantRegisterRequest> merchantRegisterRequest = merchantRegisterRequestService.findMerchantRegisterRequestByUsernameAndReviewed(signUpFormMerchant.getUsername(),false);
        if (merchantRegisterRequest.isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("no_request"), HttpStatus.OK);
        }
        if (signUpFormMerchant.getAvatar() == null || signUpFormMerchant.getAvatar().trim().isEmpty()) {
            signUpFormMerchant.setAvatar("https://firebasestorage.googleapis.com/v0/b/blog-firebase-c1eff.appspot.com/o/images%2F765-default-avatar.png?alt=media&token=913a079e-dbff-4ff1-a15b-be184446f58b");
        }
        if (signUpFormMerchant.getImageBanner() == null || signUpFormMerchant.getImageBanner().trim().isEmpty()) {
            signUpFormMerchant.setAvatar("https://firebasestorage.googleapis.com/v0/b/blog-firebase-c1eff.appspot.com/o/images%2F765-default-avatar.png?alt=media&token=913a079e-dbff-4ff1-a15b-be184446f58b");
        }
        MerchantRegisterRequest merchant = new MerchantRegisterRequest();
        merchant.setName(signUpFormMerchant.getName());
        merchant.setUsername(signUpFormMerchant.getUsername());
        merchant.setPhone(signUpFormMerchant.getPhone());
        merchant.setAddress(signUpFormMerchant.getAddress());
        merchant.setOpenTime(signUpFormMerchant.getOpenTime());
        merchant.setCloseTime(signUpFormMerchant.getCloseTime());
        merchant.setImageBanner(signUpFormMerchant.getImageBanner());
        merchantRegisterRequestService.save(merchant);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllMerchantRegisterRequest() {
        Iterable<MerchantRegisterRequest> merchantRegisterRequest = merchantRegisterRequestService.findMerchantByReviewed(false);
        return new ResponseEntity<>(merchantRegisterRequest, HttpStatus.OK);
    }

    @PostMapping("/accept/{id}")
    public ResponseEntity<?> acceptRegisterRequest(@PathVariable Long id) {
        Optional<MerchantRegisterRequest> findMerchantRegisterRequest = merchantRegisterRequestService.findById(id);
        if (!findMerchantRegisterRequest.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MerchantRegisterRequest mrr = findMerchantRegisterRequest.get();
        // tao dt merchant moi va luu db

        // sua role user thanh role merchant


        // thay doi merchanRegisterRequest ==> reviewed=true, accepted = true
        mrr.setReviewed(true);
        mrr.setAccept(true);

        //luu thay doi vao DB

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
