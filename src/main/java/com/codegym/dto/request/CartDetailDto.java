package com.codegym.dto.request;

import com.codegym.model.CartDetail;

public class CartDetailDto {
    Iterable<CartDetail> cartDetails;

    public CartDetailDto() {
    }

    public CartDetailDto(Iterable<CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }

    public Iterable<CartDetail> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(Iterable<CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }
}
