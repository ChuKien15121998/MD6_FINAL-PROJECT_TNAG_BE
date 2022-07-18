package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nameAddress;
    @ManyToOne
    private AddressCategory addressCategory;
    @ManyToOne
    private Customer customer;

    public Address() {
    }

    public Address(Long id, String nameAddress, AddressCategory addressCategory, Customer customer) {
        this.id = id;
        this.nameAddress = nameAddress;
        this.addressCategory = addressCategory;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAddress() {
        return nameAddress;
    }

    public void setNameAddress(String nameAddress) {
        this.nameAddress = nameAddress;
    }

    public AddressCategory getAddressCategory() {
        return addressCategory;
    }

    public void setAddressCategory(AddressCategory addressCategory) {
        this.addressCategory = addressCategory;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
