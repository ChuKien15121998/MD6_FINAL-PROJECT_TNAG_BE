package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Lob
    private String avatar;
    @NotBlank
    private String phoneNumber;
    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    public Customer() {
    }

    public Customer(String name, String avatar, String phoneNumber, AppUser appUser) {
        this.name = name;
        this.avatar = avatar;
        this.phoneNumber = phoneNumber;
        this.appUser = appUser;
    }

    public Customer(Long id, String name, String avatar, String phoneNumber, AppUser appUser) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.phoneNumber = phoneNumber;
        this.appUser = appUser;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
