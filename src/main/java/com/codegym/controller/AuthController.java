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
            return new ResponseEntity<>(new ResponseMessage("no_email"), HttpStatus.OK);
        }
        if (signUpFormCustomer.getAvatar() == null || signUpFormCustomer.getAvatar().trim().isEmpty()) {
            signUpFormCustomer.setAvatar("https://firebasestorage.googleapis.com/v0/b/blog-firebase-c1eff.appspot.com/o/images%2F765-default-avatar.png?alt=media&token=913a079e-dbff-4ff1-a15b-be184446f58b");
        }
        AppUser appUser = new AppUser(signUpFormCustomer.getUsername(), passwordEncoder.encode(signUpFormCustomer.getPassword()), signUpFormCustomer.getAvatar());
        Set<Role> roles = new HashSet<>();
        Role userRole = roleService.findByName(RoleName.USER).orElseThrow(() -> new RuntimeException("Role not found"));
        roles.add(userRole);
        appUser.setRoles(roles);
        userService.save(appUser);
        Customer customer = new Customer(signUpFormCustomer.getName(), signUpFormCustomer.getPhone(), appUser);
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
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getUsername(), userPrinciple.getAvatar(), userPrinciple.getAuthorities()));
    }
}
