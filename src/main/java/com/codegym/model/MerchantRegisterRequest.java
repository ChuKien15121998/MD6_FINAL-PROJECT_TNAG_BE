package com.codegym.model;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Entity
public class MerchantRegisterRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    @Pattern(regexp = "^0[0-9]{8,9}$")
    private String phone;
    @NotBlank
    private String address;
    @Column(columnDefinition = "TIME")
    private String openTime;
    @Column(columnDefinition = "TIME")
    private String closeTime;
    @Lob
    private String imageBanner;
    @Column(columnDefinition = "boolean default false")
    private boolean reviewed;
    @Column(columnDefinition = "boolean default false")
    private boolean accept;

    public MerchantRegisterRequest() {
    }

    public MerchantRegisterRequest(Long id, String name, String phone, String address, String openTime, String closeTime, String imageBanner, boolean reviewed, boolean accept) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.imageBanner = imageBanner;
        this.reviewed = reviewed;
        this.accept = accept;
    }

    public MerchantRegisterRequest(String name, String phone, String address, String openTime, String closeTime, String imageBanner, boolean reviewed, boolean accept) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.imageBanner = imageBanner;
        this.reviewed = reviewed;
        this.accept = accept;
    }

    public MerchantRegisterRequest(String name, String username, String phone, String address, String openTime, String closeTime, String imageBanner, boolean reviewed, boolean accept) {
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.imageBanner = imageBanner;
        this.reviewed = reviewed;
        this.accept = accept;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImageBanner() {
        return imageBanner;
    }

    public void setImageBanner(String imageBanner) {
        this.imageBanner = imageBanner;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }
}
