package com.codegym.controller;

import com.codegym.dto.request.ChangeAddressCustomer;
import com.codegym.dto.request.AddressForm;
import com.codegym.dto.response.ResponseMessage;
import com.codegym.model.Address;
import com.codegym.model.AppUser;
import com.codegym.model.Customer;
import com.codegym.security.userpincal.UserDetailService;
import com.codegym.service.impl.AddressService;
import com.codegym.service.impl.CustomerService;
import com.codegym.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @Autowired
    UserService userService;
    @Autowired
    CustomerService customerService;
    @Autowired
    UserDetailService userDetailService;
    
    @GetMapping
    public ResponseEntity<?> listAddressByCustomer() {
        AppUser appUser = userDetailService.getCurrentUser();
        Optional<Customer> customer = customerService.findCustomerByAppUser(appUser);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<Address> addresses = addressService.findAddressByCustomer(customer.get());
        if (addresses != null) {
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> createAddress(@RequestBody AddressForm createAddress) {
        AppUser appUser = userDetailService.getCurrentUser();
        Optional<Customer> customer = customerService.findCustomerByAppUser(appUser);
        if (!appUser.getUsername().equals("Anonymous")) {
            if (addressService.existsByNameAddress(createAddress.getNameAddress())) {
                return new ResponseEntity<>(new ResponseMessage("no_name_address"), HttpStatus.OK);
            }
            Address address = new Address(createAddress.getNameAddress(), createAddress.getAddressCategory(), customer.get());
            addressService.save(address);
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("create_failed"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> changeAddress(@PathVariable Long id, @Valid @RequestBody ChangeAddressCustomer changeAddressCustomer) {
        Optional<Address> address = addressService.findById(id);
        try {
            if (changeAddressCustomer.getNameAddress() == null) {
                return new ResponseEntity<>(new ResponseMessage("no_name"), HttpStatus.OK);
            } else {
                address.get().setNameAddress(changeAddressCustomer.getNameAddress());
                address.get().setAddressCategory(changeAddressCustomer.getAddressCategory());
                addressService.save(address.get());
            }
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
        } catch (UsernameNotFoundException exception) {
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
