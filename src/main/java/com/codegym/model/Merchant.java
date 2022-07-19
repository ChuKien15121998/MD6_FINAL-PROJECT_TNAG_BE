package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "merchant")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @Pattern(regexp = "^0[0-9]{8,9}$")
    private String phoneNumber;
    @Lob
    private String avatar;
    @Lob
    private String imageBanner;
    @Column(columnDefinition = "TIME")
    private String openTime;
    @Column(columnDefinition = "TIME")
    private String closeTime;
    @NotBlank
    private String address;
    @Column(name = "gold", columnDefinition = "boolean default false")
    private Boolean goldPartner;
    @Column(name = "isAccept", columnDefinition = "boolean default false")
    private Boolean isAccept;
    @Column(name = "isActive", columnDefinition = "boolean default true")
    private Boolean isActive;
    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    public Merchant() {
    }

    public Merchant(Long id, String name, String phoneNumber, String openTime, String closeTime, String address, Boolean goldPartner, Boolean isAccept, Boolean isActive, AppUser appUser) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.address = address;
        this.goldPartner = goldPartner;
        this.isAccept = isAccept;
        this.isActive = isActive;
        this.appUser = appUser;
    }

    public Merchant(String name, String phoneNumber, String imageBanner, String openTime, String closeTime, String address, Boolean goldPartner, Boolean isAccept, Boolean isActive, AppUser appUser) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.imageBanner = imageBanner;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.address = address;
        this.goldPartner = goldPartner;
        this.isAccept = isAccept;
        this.isActive = isActive;
        this.appUser = appUser;
    }

    public Merchant(String name, String phoneNumber, String avatar, String imageBanner, String openTime, String closeTime, String address, Boolean goldPartner, Boolean isAccept, Boolean isActive, AppUser appUser) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.imageBanner = imageBanner;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.address = address;
        this.goldPartner = goldPartner;
        this.isAccept = isAccept;
        this.isActive = isActive;
        this.appUser = appUser;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getImageBanner() {
        return imageBanner;
    }

    public void setImageBanner(String imageBanner) {
        this.imageBanner = imageBanner;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Boolean getGoldPartner() {
        return goldPartner;
    }

    public void setGoldPartner(Boolean goldPartner) {
        this.goldPartner = goldPartner;
    }

    public Boolean getAccept() {
        return isAccept;
    }

    public void setAccept(Boolean accept) {
        isAccept = accept;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
