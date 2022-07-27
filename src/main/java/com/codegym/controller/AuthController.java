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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
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
    MerchantService merchantService;
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
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CartService cartService;

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
        Customer customer = new Customer(signUpFormCustomer.getName(), signUpFormCustomer.getAvatar(), signUpFormCustomer.getPhone(), appUser);
        customerService.save(customer);
        AddressCategory addressCategory = signUpFormCustomer.getAddressCategory();
        Address address = new Address(signUpFormCustomer.getAddress(), addressCategory, customer);
        addressService.save(address);
        Cart cart = new Cart(customer);
        cartService.save(cart);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @PostMapping("customer/signin")
    public ResponseEntity<?> loginCustomer(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword())
        );
        // Thêm đối tượng này vào security để xử lý tiếp
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Khởi tạo jwt từ đối tượng này
        String token = jwtProvider.createToken(authentication);
        // Tạo đối tượng userprinciple từ authentication.getPrincipal
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        Optional<AppUser> appUser = userService.findByUsername(signInForm.getUsername());
        if(appUser.get().getUsername().equals("admin@gmail.com")){
            return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getUsername(),userPrinciple.getAuthorities()));

        }
        Optional<Customer> customer = customerService.findCustomerByAppUser(appUser.get());
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getUsername(),customer.get().getName(), customer.get().getAvatar(), userPrinciple.getAuthorities()));
    }

    @PostMapping("merchant/signin")
    public ResponseEntity<?> loginMerchant(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword())
        );
        // Thêm đối tượng này vào security để xử lý tiếp
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Khởi tạo jwt từ đối tượng này
        String token = jwtProvider.createToken(authentication);
        // Tạo đối tượng userprinciple từ authentication.getPrincipal
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        Optional<AppUser> appUser = userService.findByUsername(signInForm.getUsername());
        Optional<Merchant> merchant = merchantService.findMerchantByAppUser(appUser.get());
        if (!merchant.isPresent() || !merchant.get().isActive()) {
            return new ResponseEntity<>(new ResponseMessage("no_signin"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getUsername(), userPrinciple.getAuthorities()));
    }

    @PostMapping("merchant/signup")
    public ResponseEntity<?> registerMerchant(@Valid @RequestBody SignUpFormMerchant signUpFormMerchant) {
        if (!signUpFormMerchant.getPassword().equals(signUpFormMerchant.getConfirmPassword())) {
            return new ResponseEntity<>(new ResponseMessage("no_password"), HttpStatus.OK);
        }
        Optional<MerchantRegisterRequest> merchantRegisterRequest = merchantRegisterRequestService.findMerchantRegisterRequestByUsernameAndReviewed(signUpFormMerchant.getUsername(), false);
        if (merchantRegisterRequest.isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("no_request"), HttpStatus.OK);
        }
        if (signUpFormMerchant.getAvatar() == null || signUpFormMerchant.getAvatar().trim().isEmpty()) {
            signUpFormMerchant.setAvatar("https://firebasestorage.googleapis.com/v0/b/blog-firebase-c1eff.appspot.com/o/images%2F765-default-avatar.png?alt=media&token=913a079e-dbff-4ff1-a15b-be184446f58b");
        }
        if (signUpFormMerchant.getImageBanner() == null || signUpFormMerchant.getImageBanner().trim().isEmpty()) {
            signUpFormMerchant.setImageBanner("https://firebasestorage.googleapis.com/v0/b/blog-firebase-c1eff.appspot.com/o/images%2F765-default-avatar.png?alt=media&token=913a079e-dbff-4ff1-a15b-be184446f58b");
        }
        MerchantRegisterRequest merchant = new MerchantRegisterRequest();
        merchant.setName(signUpFormMerchant.getName());
        merchant.setUsername(signUpFormMerchant.getUsername());
        merchant.setPassword(passwordEncoder.encode(signUpFormMerchant.getPassword()));
        merchant.setPhone(signUpFormMerchant.getPhone());
        merchant.setAddress(signUpFormMerchant.getAddress());
        merchant.setOpenTime(signUpFormMerchant.getOpenTime());
        merchant.setCloseTime(signUpFormMerchant.getCloseTime());
        merchant.setAvatar(signUpFormMerchant.getAvatar());
        merchant.setImageBanner(signUpFormMerchant.getImageBanner());
        merchantRegisterRequestService.save(merchant);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @GetMapping("merchant/request")
    public ResponseEntity<?> findAllMerchantRegisterRequest() {
        Iterable<MerchantRegisterRequest> merchantRegisterRequest = merchantRegisterRequestService.findMerchantByReviewed(false);
        return new ResponseEntity<>(merchantRegisterRequest, HttpStatus.OK);
    }

    @PostMapping("/accept/{id}")
    public ResponseEntity<?> acceptRegisterRequest(@PathVariable Long id, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        Optional<MerchantRegisterRequest> findMerchantRegisterRequest = merchantRegisterRequestService.findById(id);
        if (!findMerchantRegisterRequest.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MerchantRegisterRequest mrr = findMerchantRegisterRequest.get();
        AppUser appUser;
        Set<Role> roles = new HashSet<>();
        if (userService.existsByUsername(mrr.getUsername())) {
             appUser = userService.findByUsername(mrr.getUsername()).get();
             Role merchantRole = roleService.findByName(RoleName.MERCHANT).orElseThrow(() -> new RuntimeException("Role not found"));
             Role userRole = roleService.findByName(RoleName.USER).orElseThrow(() -> new RuntimeException("Role not found"));
             roles.add(merchantRole);
             roles.add(userRole);
             appUser.setRoles(roles);
        } else {
            appUser = new AppUser(mrr.getUsername(), (mrr.getPassword()));
            Role merchantRole = roleService.findByName(RoleName.MERCHANT).orElseThrow(() -> new RuntimeException("Role not found"));
            Role userRole = roleService.findByName(RoleName.USER).orElseThrow(() -> new RuntimeException("Role not found"));
            roles.add(merchantRole);
            roles.add(userRole);
            appUser.setRoles(roles);
        }
            userService.save(appUser);
            // tao dt merchant moi va luu db
            Merchant merchant = new Merchant();
            merchant.setName(mrr.getName());
            merchant.setPhoneNumber(mrr.getPhone());
            merchant.setAvatar(mrr.getAvatar());
            merchant.setImageBanner(mrr.getImageBanner());
            merchant.setOpenTime(mrr.getOpenTime());
            merchant.setCloseTime(mrr.getCloseTime());
            merchant.setAddress(mrr.getAddress());
            merchant.setAppUser(appUser);
            merchant.setAccept(true);
            merchant.setGoldPartner(false);
            merchant.setActive(true);
            mrr.setReviewed(true);
            mrr.setAccept(true);
            //luu thay doi vao DB
            merchantService.save(merchant);
            merchantRegisterRequestService.save(mrr);
            String siteURL = getSiteURL(request);
            sendEmailAccept(appUser, merchant, siteURL);
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    public void sendEmailAccept(AppUser appUser,Merchant merchant, String siteURL) throws UnsupportedEncodingException, MessagingException {
        String signURL =siteURL + "/signin";
        System.out.println(signURL);
        String toAddress = appUser.getUsername();
        String fromAddress = "chuvankien151298@gmail.com";
        String senderName = "What will you have for lunch?";
        String subject = "Thanks for your registration as merchant";
        String mailContent = "<p>Dear " + merchant.getName()+",</p>";
        mailContent += "<p>Your registration is success</p>";
        mailContent += "<p>Please click the link below to login:</p>";
        mailContent += "<h3><a href=\""+signURL+"\">LOGIN</a></h3>";
        mailContent += "<p>Thank you<br>Group What will you have for lunch?</p>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress,senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(mailContent, true);
        mailSender.send(message);
        System.out.println("Email has been sent");
    }
    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @PostMapping("/refuse/{id}")
    public ResponseEntity<?> refuseRegisterRequest(@PathVariable Long id, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        Optional<MerchantRegisterRequest> findMerchantRegisterRequest = merchantRegisterRequestService.findById(id);
        if (!findMerchantRegisterRequest.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MerchantRegisterRequest mrr = findMerchantRegisterRequest.get();
        mrr.setReviewed(true);
        mrr.setAccept(false);
        merchantRegisterRequestService.save(mrr);
        String siteURL = getSiteURL(request);
        sendEmailRefuse(mrr, siteURL);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void sendEmailRefuse(MerchantRegisterRequest merchantRegisterRequest, String siteURL) throws UnsupportedEncodingException, MessagingException {
        String signURL =siteURL + "/signin";
        System.out.println(signURL);
        String toAddress = merchantRegisterRequest.getUsername();
        String fromAddress = "chuvankien151298@gmail.com";
        String senderName = "What will you have for lunch?";
        String subject = "Thanks for your registration as merchant";
        String mailContent = "<p>Dear " + merchantRegisterRequest.getName()+",</p>";
        mailContent += "<p>Your registration has been refused</p>";
        mailContent += "<p>Thank you<br>Group What will you have for lunch?</p>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress,senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(mailContent, true);
        mailSender.send(message);
        System.out.println("Email has been sent");
    }

}
