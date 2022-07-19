package com.codegym.dto.request;

public class SignUpFormMerchant {
    private String name;
    private String username;
    private String phone;
    private String address;
    private String openTime;
    private String closeTime;
    private String password;
    private String confirmPassword;
    private String avatar;
    private String imageBanner;

    public SignUpFormMerchant() {
    }

    public SignUpFormMerchant(String name, String username, String phone, String address, String openTime, String closeTime, String password, String confirmPassword, String avatar) {
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.avatar = avatar;
    }
    public SignUpFormMerchant(String name, String username, String phone, String address, String openTime, String closeTime, String password, String confirmPassword) {
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public SignUpFormMerchant(String name, String username, String phone, String address, String openTime, String closeTime, String password, String confirmPassword, String avatar, String imageBanner) {
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.avatar = avatar;
        this.imageBanner = imageBanner;
    }

    public String getImageBanner() {
        return imageBanner;
    }

    public void setImageBanner(String imageBanner) {
        this.imageBanner = imageBanner;
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

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
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
