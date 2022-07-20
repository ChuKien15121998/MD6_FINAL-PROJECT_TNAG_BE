package com.codegym.dto.request;

import javax.validation.constraints.NotBlank;

public class ChangeProfileCustomForm {
    private String name;
    private String phoneNumber;
    public ChangeProfileCustomForm() {
    }

    public ChangeProfileCustomForm(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
