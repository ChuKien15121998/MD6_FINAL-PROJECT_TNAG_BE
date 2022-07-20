package com.codegym.dto.request;

import com.codegym.model.AddressCategory;

public class CreateAddress {
    private String nameAddress;
    private AddressCategory addressCategory;

    public CreateAddress() {
    }

    public CreateAddress(String nameAddress, AddressCategory addressCategory) {
        this.nameAddress = nameAddress;
        this.addressCategory = addressCategory;
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
}
