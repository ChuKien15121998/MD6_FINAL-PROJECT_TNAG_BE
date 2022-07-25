package com.codegym.service;

import com.codegym.model.AppUser;
import com.codegym.model.Cart;
import com.codegym.model.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IMerchantService extends IGeneralService<Merchant> {
    Iterable<Merchant> findMerchantByNameContaining(String name);
    Optional<Merchant> findMerchantByAppUser_Id(Long id);
    Page<Merchant> findAll(Pageable pageable);

    Optional<Merchant> findById(Long id);

    Merchant save (Merchant merchant);

    void deleteById(Long id);

    Optional<Merchant> findMerchantByAppUser(AppUser appUser);

    Iterable<Merchant> getListMerchantInCart(Cart cart);

    Iterable<Merchant> findMerchantByGoldPartnerTrue();

}
