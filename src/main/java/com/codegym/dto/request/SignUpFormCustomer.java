package com.codegym.dto.request;

import com.codegym.model.AddressCategory;

public class SignUpFormCustomer {
    private String name;
    private String username;
    private String phone;
    private String address;
    private AddressCategory addressCategory;
    private String password;
    private String confirmPassword;
    private String avatar;

    public SignUpFormCustomer() {
    }

    public SignUpFormCustomer(String name, String username, String phone, String address, AddressCategory addressCategory, String password, String confirmPassword, String avatar) {
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.addressCategory = addressCategory;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.avatar = avatar;
    }

    public AddressCategory getAddressCategory() {
        return addressCategory;
    }

    public void setAddressCategory(AddressCategory addressCategory) {
        this.addressCategory = addressCategory;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
