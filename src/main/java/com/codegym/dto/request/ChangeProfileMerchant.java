package com.codegym.dto.request;

import javax.validation.constraints.Pattern;

public class ChangeProfileMerchant {

    private String name;
    private String openTime;
    private String closeTime;
    private String address;

    public ChangeProfileMerchant() {
    }

    public ChangeProfileMerchant(String name, String openTime, String closeTime, String address) {
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
